package co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder;

import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductResponse;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
         String codeOrder,
         LocalDate orderDate,
         Integer quantity,
         Boolean isDeleted,

        String phoneCustomer,
        String codePayment,

        String contactPhoneShipper,
        List<ProductResponse> codeProducts
) {
}
