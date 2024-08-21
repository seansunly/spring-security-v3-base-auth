package co.ruppcstat.ecomercv1.ecomV1.feature.order;

import co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder.OrderCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderCreate orderCreate);
    OrderResponse getOrder( String codeOrder);
    OrderResponse deleteOrder( String codeOrder);
    List<OrderResponse> getOrders();
    void deleteByCodeOrder( String codeOrder);

}
