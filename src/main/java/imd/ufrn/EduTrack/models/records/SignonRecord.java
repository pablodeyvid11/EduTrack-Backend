package imd.ufrn.EduTrack.models.records;

import imd.ufrn.EduTrack.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignonRecord(
		@NotNull
		@NotBlank(message = "O nome não pode estar em branco")
		@Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
		String name,
		@NotNull
		@Email(message = "Email deve ser válido")
		@NotBlank(message = "Email não pode estar em branco")
		String email,
		@NotNull
		@NotBlank(message = "O username não pode estar em branco")
		@Size(min = 5, max = 50, message = "O username deve ter entre 5 e 50 caracteres")
		String username,
		@NotNull
		@ValidPassword
		String password,
		String bio) {
}