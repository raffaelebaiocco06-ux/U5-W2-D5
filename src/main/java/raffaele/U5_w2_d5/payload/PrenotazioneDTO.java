package raffaele.U5_w2_d5.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PrenotazioneDTO(
        @NotNull(message = "Id dipendente obbligatorio")
        Long dipendenteId,

        @NotNull(message = "Id viaggio obbligatorio")
        Long viaggioId,

        @NotNull(message = "data obbligatoria")
        LocalDate dataRichiesta,
        @Size(max =200 , message = "descrizione massimo 200 caratteri")
        String descrizione
) {}
