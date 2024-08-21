package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import lombok.Builder;

@Builder
public record ResponseTokenV1(
        //token type
        String tokenType,
        String accessToken,
        String refreshToken
) {
}
