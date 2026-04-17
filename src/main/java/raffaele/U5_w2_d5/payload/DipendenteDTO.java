package raffaele.U5_w2_d5.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank(message = "Username obbligatorio")
        @Size(min = 2, max = 30, message = "username deve essere compreso tra i 2 e i 30 caratteri")
        String username,
        @NotBlank(message = "Nome obbligatorio")
        @Size(min = 2, max = 30, message = "Il nome proprio deve essere compreso tra i 2 e i 30 caratteri")
        String nome,
        @NotBlank(message = "Cognome obbligatorio")
        @Size(min = 2, max = 30, message = "Il cognome proprio deve essere compreso tra i 2 e i 30 caratteri")
        String cognome,
        @NotBlank(message = "Email obbligatoria")
        @Email(message = "L'email inserita non è nel formato corretto")
        String email

) {
}
