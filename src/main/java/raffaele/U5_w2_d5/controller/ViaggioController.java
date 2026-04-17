package raffaele.U5_w2_d5.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raffaele.U5_w2_d5.entities.Viaggio;
import raffaele.U5_w2_d5.exeptionn.ValidationException;
import raffaele.U5_w2_d5.payload.ViaggioDTO;
import raffaele.U5_w2_d5.service.ViaggioService;

import java.util.List;


@RestController
@RequestMapping("/viaggio")
public class ViaggioController {
    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Viaggio salva(@RequestBody @Validated ViaggioDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        return this.viaggioService.salva(body);
    }

    @GetMapping
    public Page<Viaggio> getAllViaggi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return viaggioService.findAll(page, size, sortBy);
    }
    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable Long id) {
        return viaggioService.findById(id);
    }
  @PatchMapping("/{id}/stato")
  public Viaggio updateStato(@PathVariable Long id,@RequestBody String newstato){
        return viaggioService.updateStato(id,newstato);
  }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable Long id) {
        viaggioService.findByIdAndDelete(id);
    }


}
