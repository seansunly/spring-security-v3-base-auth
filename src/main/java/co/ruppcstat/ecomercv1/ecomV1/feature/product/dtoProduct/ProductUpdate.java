package co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductUpdate(
        String image,
        String name,
        String description,
        Float price,
        Boolean isDeleted
) {
}
