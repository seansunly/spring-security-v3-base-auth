package co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory;

import lombok.Builder;

@Builder
public record CategoryResponse(
        String name,
        String description,
        Boolean isDeleted
) {
}
