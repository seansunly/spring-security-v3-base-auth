package co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper;

public record ShipperResponse(
        String name,
        String contactPhone,
        String contactAddress,
        Boolean isDeleted
) {
}
