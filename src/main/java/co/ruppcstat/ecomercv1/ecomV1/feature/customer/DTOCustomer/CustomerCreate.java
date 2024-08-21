package co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer;

import jakarta.persistence.Column;

public record CustomerCreate(
         String name,
         String phone,
         String address,
         String type,
         String email
       //  Boolean isDeleted
) {
}
