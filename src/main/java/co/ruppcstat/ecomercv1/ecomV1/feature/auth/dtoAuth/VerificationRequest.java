package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import jakarta.validation.constraints.NotBlank;

public record VerificationRequest(
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Verified code is required")
        String verifiedCode
) {
}
