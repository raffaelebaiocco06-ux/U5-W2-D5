package raffaele.U5_w2_d5.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;
import raffaele.U5_w2_d5.enumm.StatoViaggio;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "destinazione obbligatoria")
        String destinazione,

        @NotBlank
        @FutureOrPresent(message = "data obbligatoria")
        LocalDate data,

        @NotBlank(message = "stato obbligatorio")
        StatoViaggio stato

) {


}
