package co.ruppcstat.ecomercv1.ecomV1.feature.category;

import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryUpdate;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryCreate categoryCreate);
    CategoryResponse updateCategory(String name,CategoryUpdate categoryUpdate);
    void deleteCategory(String name);
    CategoryResponse getCategory(String name);
    List<CategoryResponse> getCategories();
    CategoryResponse isDeletedCategory(String name);

}
