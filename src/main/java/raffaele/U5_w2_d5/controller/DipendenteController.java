package raffaele.U5_w2_d5.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import raffaele.U5_w2_d5.entities.Dipendente;
import raffaele.U5_w2_d5.exeptionn.BadRequestException; // Assicurati sia il nome corretto del package
import raffaele.U5_w2_d5.exeptionn.ValidationException;
import raffaele.U5_w2_d5.payload.DipendenteDTO;
import raffaele.U5_w2_d5.service.DipendenteService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente salva(@RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        return this.dipendenteService.salva(body);
    }

    @GetMapping
    public Page<Dipendente> getAllDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable Long id) {
        dipendenteService.findByIdAndDelete(id);
    }

    @PatchMapping("/{id}/avatar")
    public Dipendente uploadAvatar(@RequestParam("profile_picture") MultipartFile file, @PathVariable long id) {

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());

       return this.dipendenteService.avatarUpload(file, id);

    }
}
