package co.ruppcstat.ecomercv1.ecomV1.feature.product;

import co.ruppcstat.ecomercv1.ecomV1.deman.Category;
import co.ruppcstat.ecomercv1.ecomV1.deman.Product;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.CategoryRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductUpdate;
import co.ruppcstat.ecomercv1.ecomV1.mapper.CategoryMapper;
import co.ruppcstat.ecomercv1.ecomV1.mapper.ProductMapper;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public ProductResponse createProduct(ProductCreate productCreate) {
        if(productRepository.existsByKeyId(productCreate.keyId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
        }
        Category category=categoryRepository.findByName(productCreate.categoryName())
                .orElseThrow(()->new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Category name not found"));

        //categoryRepository.save(category);
       Product product=productMapper.createProduct(productCreate);
       product.setCategory(category);
       product.setIsDeleted(false);
      product= productRepository.save(product);
       return productMapper.entityToResponse(product);
    }
    @Override
    public ProductResponse updateProduct(String keyId, ProductUpdate productUpdate) {
        Product product=productRepository.findByKeyId(keyId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Product key id is not found"));
        productMapper.updateProduct(product, productUpdate);
        product= productRepository.save(product);
        return productMapper.entityToResponse(product);
    }

    @Override
    public void deleteProduct(String keyId) {
        Product product=productRepository.findByKeyId(keyId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Product key is not found"));
        productRepository.delete(product);

    }

    @Override
    public ProductResponse getProduct(String keyId) {
        Product product=productRepository.findByKeyId(keyId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));
       product= productRepository.save(product);
        return productMapper.entityToResponse(product);
    }

    @Override
    public Page<ProductResponse> getProducts(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "productID");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Product> products=productRepository.findAll(pageRequest);
        return products.map(productMapper::entityToResponse);
    }

    @Override
    public ProductResponse isDeletedProduct(String keyId) {
        Product product=productRepository.findByKeyId(keyId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));
        product.setIsDeleted(true);
        product= productRepository.save(product);
        return productMapper.entityToResponse(product);
    }
}
