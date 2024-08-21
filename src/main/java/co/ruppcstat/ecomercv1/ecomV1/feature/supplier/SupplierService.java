package co.ruppcstat.ecomercv1.ecomV1.feature.supplier;

import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierUpdate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupplierService {
    SupplierResponse createSupplier(SupplierCreate supplierCreate);
    SupplierResponse updateSupplier(String contactPhone,SupplierUpdate supplierUpdate);
    void deleteSupplier(String contactPhone);
    SupplierResponse getSupplier(String contactPhone);
    Page<SupplierResponse> getAllSuppliers(int pageNumber, int pageSize);
    SupplierResponse updateDeletedSupplier(String contactPhone);
}
