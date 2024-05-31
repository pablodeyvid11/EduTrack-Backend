package imd.ufrn.EduTrack.models.records;

import imd.ufrn.EduTrack.annotations.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SigninRecord(
		@NotNull
		@NotBlank(message = "O username não pode estar em branco")
		@Size(min = 5, max = 50, message = "O username deve ter entre 5 e 50 caracteres")
		String username,
		@NotNull
		@ValidPassword
		String password) {
}