package co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO;

public record SupplierUpdate(
        String name,
        String contactPhone,
        String contactAddress,
        Boolean isDeleted
) {
}
