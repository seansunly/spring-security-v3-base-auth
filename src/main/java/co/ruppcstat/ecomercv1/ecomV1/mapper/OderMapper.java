package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Oder;
import co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder.OrderCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface OderMapper {
    @Mapping(source = "phoneCustomer",target = "customer.phone")
    @Mapping(source = "codePayment",target = "payment.codePayment")
    @Mapping(source = "contactPhoneShipper",target = "shipper.contactPhone")
   // @Mapping(source = "codeProducts",target = "products.keyId")
   // @Mapping(source = "keyId",target = "products.keyId")
    Oder createOder(OrderCreate orderCreate);

    @Mapping(target = "phoneCustomer",source = "customer.phone")
    @Mapping(target = "codePayment",source = "payment.codePayment")
    @Mapping(target = "contactPhoneShipper",source = "shipper.contactPhone")
    @Mapping(source = "products",target = "codeProducts")
    OrderResponse entityToResponse(Oder oder);
    List<OrderResponse> entityToResponseList(List<Oder> oderList);
}
