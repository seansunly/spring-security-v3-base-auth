package co.ruppcstat.ecomercv1.ecomV1.feature.product;

import co.ruppcstat.ecomercv1.ecomV1.deman.Product;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductCreate productCreate) {
        return productService.createProduct(productCreate);
    }
    @GetMapping
    public Page<ProductResponse> getProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize) {
        return productService.getProducts(pageNumber,pageSize);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{keyId}")
    public void deleteProduct(@PathVariable String keyId) {
         productService.deleteProduct(keyId);
    }
    @PatchMapping("/{keyId}")
    public ProductResponse updateProduct(@PathVariable String keyId, @RequestBody ProductUpdate productUpdate) {
        return productService.updateProduct(keyId, productUpdate);
    }
    @GetMapping("/{keyId}")
    public ProductResponse getProduct(@PathVariable String keyId) {
        return productService.getProduct(keyId);
    }
    @PatchMapping("/{keyId}/deleteProduct")
    public ProductResponse isDeletedProduct(@PathVariable String keyId) {
        return productService.isDeletedProduct(keyId);
    }

}
