package co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO;

public record SupplierResponse(
        String name,
        String contactPhone,
        String contactAddress,
        Boolean isDeleted
) {
}
