package co.ruppcstat.ecomercv1.ecomV1.feature.sale;

import co.ruppcstat.ecomercv1.ecomV1.deman.Sale;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {
    private final SalService salService;

    @PostMapping
    public SaleResponse createSale(@Valid @RequestBody SaleCreate saleCreate) {
        return salService.createSale(saleCreate);
    }
    @GetMapping
    public Page<SaleResponse> getSalAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize){
        return salService.getSales(pageNumber,pageSize);
    }
    @GetMapping("/{codeSale}")
    public SaleResponse getSale(@PathVariable String codeSale) {
        return salService.getSale(codeSale);
    }
    @DeleteMapping("/{codeSale}")
    public void deleteSale(@PathVariable String codeSale) {
        salService.deleteSale(codeSale);
    }
    @PatchMapping("/{codeSale}")
    public SaleResponse updateIsDeleted(@PathVariable String codeSale){
        return salService.isDeletedSale(codeSale);
    }
}
