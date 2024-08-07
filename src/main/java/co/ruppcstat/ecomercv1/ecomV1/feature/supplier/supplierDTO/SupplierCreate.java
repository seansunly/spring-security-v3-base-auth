package co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record SupplierCreate(
         String name,
         @NotNull(message = "supplier phoneNumber is not null")
         String contactPhone,
         String contactAddress
         //Boolean isDeleted
) {
}
