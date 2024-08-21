package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResetPasswordRequest(
        @NotBlank(message = "email is request input")
        String email
) {
}
