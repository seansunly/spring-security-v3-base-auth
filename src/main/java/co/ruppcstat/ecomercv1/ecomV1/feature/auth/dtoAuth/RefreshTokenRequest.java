package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "refresh token is request input ")
        String refreshToken
) {
}
