package co.ruppcstat.ecomercv1.ecomV1.feature.category;

import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categorys")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public Page<CategoryResponse> getAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize) {
        return categoryService.getCategories(pageNumber,pageSize);
    }
    @GetMapping("/{name}")
    public CategoryResponse getCategory(@PathVariable String name) {
        return categoryService.getCategory(name);
    }
    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryCreate categoryCreate){
        return categoryService.createCategory(categoryCreate);
    }
    @PatchMapping("/{name}")
    public CategoryResponse updateCategory(@PathVariable String name,@RequestBody CategoryUpdate categoryUpdate){
        return categoryService.updateCategory(name, categoryUpdate);
    }
    @DeleteMapping("/{name}")
    public void deleteCategory(@PathVariable String name){
        categoryService.deleteCategory(name);
    }
    @PatchMapping("/{name}/deleted")
    public CategoryResponse isDeletedCategory(@PathVariable String name){
        return categoryService.isDeletedCategory(name);
    }

}
