package co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper;

public record ShipperUpdate(
        String name,
        String contactPhone,
        String contactAddress,
        Boolean isDeleted
) {
}
