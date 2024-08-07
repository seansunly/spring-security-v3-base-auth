package co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory;

import jakarta.validation.constraints.NotNull;

public record CategoryCreate(
        @NotNull(message = "please input name category ")
         String name,
         String description,
         Boolean isDeleted
) {
}
