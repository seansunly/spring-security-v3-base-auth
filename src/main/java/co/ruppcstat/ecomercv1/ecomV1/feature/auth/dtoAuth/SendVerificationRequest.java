package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import jakarta.validation.constraints.NotBlank;

public record SendVerificationRequest(
        @NotBlank(message = "Email is required")
        String email
) {
}
