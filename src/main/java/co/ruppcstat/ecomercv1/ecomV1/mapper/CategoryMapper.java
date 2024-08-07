package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Category;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category createCategory(CategoryCreate categoryCreate);
    CategoryResponse entityToResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(@MappingTarget Category category, CategoryUpdate categoryUpdate);
    List<CategoryResponse> entityListToResponse(List<Category> categoryList);
}
