package raffaele.U5_w2_d5.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raffaele.U5_w2_d5.entities.Prenotazione;
import raffaele.U5_w2_d5.exeptionn.BadRequestException;
import raffaele.U5_w2_d5.exeptionn.ValidationException; // Se hai una classe specifica per gli errori di validazione
import raffaele.U5_w2_d5.payload.PrenotazioneDTO;
import raffaele.U5_w2_d5.service.PrenotazioneService;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Prenotazione salva(@RequestBody @Validated PrenotazioneDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        return this.prenotazioneService.salva(body);
    }

    @GetMapping
    public Page<Prenotazione> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return prenotazioneService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Prenotazione getPById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteP(@PathVariable Long id) {
        prenotazioneService.findByIdAndDelete(id);
    }
}

