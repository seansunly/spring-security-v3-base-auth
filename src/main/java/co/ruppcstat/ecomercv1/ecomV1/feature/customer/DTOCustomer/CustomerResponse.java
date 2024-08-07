package co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer;

public record CustomerResponse(
        String name,
        String phone,
        String address,
        String type,
        String email,
        Boolean isDeleted

) {
}
