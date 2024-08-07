package co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder;

import co.ruppcstat.ecomercv1.ecomV1.deman.Customer;
import co.ruppcstat.ecomercv1.ecomV1.deman.Payment;
import co.ruppcstat.ecomercv1.ecomV1.deman.Product;
import co.ruppcstat.ecomercv1.ecomV1.deman.Shipper;

import java.util.List;

public record OrderCreate(
        Integer quantity,
        String phoneCustomer,
        String codePayment,
        String contactPhoneShipper,
        List<String> codeProducts
) {
}
