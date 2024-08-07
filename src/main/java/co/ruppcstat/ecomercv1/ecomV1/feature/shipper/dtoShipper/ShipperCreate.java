package co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record ShipperCreate(
        @NotNull(message = "please input your name shipper")
         String name,
         @NotNull(message = "please input your phone number ")
         String contactPhone,
         @NotNull(message = "please input your address")
         String contactAddress,
         Boolean isDeleted
) {
}
