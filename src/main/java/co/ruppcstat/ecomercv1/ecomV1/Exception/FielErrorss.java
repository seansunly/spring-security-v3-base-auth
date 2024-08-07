package co.ruppcstat.ecomercv1.ecomV1.Exception;

import lombok.Builder;

@Builder
public record FielErrorss(
        String field,
        String detail
) {
}
