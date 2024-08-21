package co.ruppcstat.ecomercv1.ecomV1.feature.category;

import co.ruppcstat.ecomercv1.ecomV1.deman.Category;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.DTOCategory.CategoryUpdate;
import co.ruppcstat.ecomercv1.ecomV1.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryResponse createCategory(CategoryCreate categoryCreate) {
        if(categoryRepository.existsByName(categoryCreate.name())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "name category already exists");
        }
        Category category = categoryMapper.createCategory(categoryCreate);
        category.setIsDeleted(false);
        category= categoryRepository.save(category);
        return categoryMapper.entityToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(String name, CategoryUpdate categoryUpdate) {
        Category category=categoryRepository.findByName(name).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"name is not found"));
        categoryMapper.updateCategory(category,categoryUpdate);
        category= categoryRepository.save(category);
        return categoryMapper.entityToResponse(category);
    }

    @Override
    public void deleteCategory(String name) {
        Category category=categoryRepository.findByName(name).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"name is not found"));
        categoryRepository.delete(category);
    }
    @Override
    public CategoryResponse getCategory(String name) {
        Category category=categoryRepository.findByName(name).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"name is not found"));
       category= categoryRepository.save(category);
        return categoryMapper.entityToResponse(category);
    }

    @Override
    public Page<CategoryResponse> getCategories(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "categoryID");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Category> categoryList=categoryRepository.findAll(pageRequest);
        return categoryList.map(categoryMapper::entityToResponse);
    }

    @Override
    public CategoryResponse isDeletedCategory(String name) {
        Category category=categoryRepository.findByName(name).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"name is not found"));
        category.setIsDeleted(true);
        categoryRepository.save(category);
        return categoryMapper.entityToResponse(category);
    }
}
