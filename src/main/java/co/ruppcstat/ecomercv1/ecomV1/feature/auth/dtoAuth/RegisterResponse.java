package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import lombok.Builder;

@Builder
public record RegisterResponse(
        String message,
        String email
) {
}
