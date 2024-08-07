package co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport;

import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierResponse;

import java.time.LocalDate;

public record ImportResponse(
        String codeNumber,
        String importDate,
        Float totalAmount,
        Boolean isDeleted,
        StaffResponse staff,
        SupplierResponse supplier
) {
}
