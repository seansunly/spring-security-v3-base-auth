package co.ruppcstat.ecomercv1.ecomV1.feature.sale;

import co.ruppcstat.ecomercv1.ecomV1.deman.Sale;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public List<SaleResponse> getSalAll(){
        return salService.getSales();
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
