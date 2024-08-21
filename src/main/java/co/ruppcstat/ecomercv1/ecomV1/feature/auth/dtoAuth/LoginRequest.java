package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "phoneNumber is request")
        String phoneNumber,
        @NotBlank(message = "password is request")
        String password

) {
}
