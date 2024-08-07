package co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct;

import co.ruppcstat.ecomercv1.ecomV1.deman.Category;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryResponse;
import lombok.Builder;

@Builder
public record ProductResponse(
        String keyId,
        String image,
        String name,
        String description,
        Float price,
        Boolean isDeleted,
        CategoryResponse category
      //  Category category
        //Integer categoryId
) {
}
