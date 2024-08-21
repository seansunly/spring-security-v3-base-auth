package co.ruppcstat.ecomercv1.ecomV1.feature.supplier;

import co.ruppcstat.ecomercv1.ecomV1.deman.Supplier;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")
@RestController
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping
    public SupplierResponse createSupplier(@Valid @RequestBody SupplierCreate supplierCreate) {
        return supplierService.createSupplier(supplierCreate);
    }
    @GetMapping("/{contactPhone}")
    public SupplierResponse getSupplier(@PathVariable String contactPhone) {
        return supplierService.getSupplier(contactPhone);
    }
    @GetMapping
    public Page<SupplierResponse> getAllSuppliers(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return supplierService.getAllSuppliers(pageNumber,pageSize);
    }

    @DeleteMapping("/{contactPhone}")
    public void deleteSupplier(@PathVariable String contactPhone) {
        supplierService.deleteSupplier(contactPhone);
    }

    @PatchMapping("/{contactPhone}")
    public SupplierResponse updateSupplier(@Valid @PathVariable String contactPhone, @RequestBody SupplierUpdate supplierUpdate) {
        return supplierService.updateSupplier(contactPhone, supplierUpdate);
    }
    @PatchMapping("/{contactPhone}/isDeleted")
    public SupplierResponse isDeleted(@PathVariable String contactPhone) {
        return supplierService.updateDeletedSupplier(contactPhone);
    }

}
