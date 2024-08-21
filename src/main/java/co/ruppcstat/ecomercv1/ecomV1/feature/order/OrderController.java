package co.ruppcstat.ecomercv1.ecomV1.feature.order;

import co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder.OrderCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody OrderCreate orderCreate) {
        return orderService.createOrder(orderCreate);
    }
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getOrders();
    }
    @GetMapping("/{codeOrder}")
    public OrderResponse getOrder(@PathVariable String codeOrder) {
        return orderService.getOrder(codeOrder);
    }
    @PatchMapping("/{codeOrder}/isDeleted")
    public OrderResponse updateIsDeleted(@PathVariable String codeOrder) {
       return orderService.deleteOrder(codeOrder);
    }
    @DeleteMapping("/{codeOrder}")
    public void deleteOrder(@PathVariable String codeOrder) {
        orderService.deleteByCodeOrder(codeOrder);
    }
}
