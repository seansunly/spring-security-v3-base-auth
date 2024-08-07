package co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record ProductCreate(
        @NotNull(message = "input keyId")
        String keyId,
        @NotNull(message = "please input image products")
         String image,
         @NotNull(message = "input name product")
         String name,
         String description,
         @NotBlank(message = "input price products")
         Float price,
         Boolean isDeleted,
//         @NotBlank(message = "input name category")
         String categoryName
        //Integer categoryId
) {
}
