package raffaele.U5_w2_d5.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "destinazione obbligatoria")
        String destinazione,

        @NotNull
        @FutureOrPresent(message = "data obbligatoria")
        LocalDate data,

        @NotBlank(message = "stato obbligatorio")
        String stato

) {


}
