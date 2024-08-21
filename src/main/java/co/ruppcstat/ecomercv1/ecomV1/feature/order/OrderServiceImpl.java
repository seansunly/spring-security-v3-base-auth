package co.ruppcstat.ecomercv1.ecomV1.feature.order;


import co.ruppcstat.ecomercv1.ecomV1.deman.*;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.CustomerRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder.OrderCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.order.dtoOrder.OrderResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.PaymentRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.ProductRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.ShipperRepository;
import co.ruppcstat.ecomercv1.ecomV1.mapper.OderMapper;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    private final ShipperRepository shipperRepository;
    private final ProductRepository productRepository;
    private final OderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderCreate orderCreate) {
        Customer customer =customerRepository.findByPhone(orderCreate.phoneCustomer())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone not found"));

        Payment payment=paymentRepository.findByCodePayment(orderCreate.codePayment())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment code not found"));

        Shipper shipper=shipperRepository.findByContactPhone(orderCreate.contactPhoneShipper())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shipper phone not found"));

        //Product product=productRepository.findByKeyId(String.valueOf(orderCreate.codeProducts()))
                //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        //
        List<String> keyIdList = orderCreate.codeProducts();
        List<Product> productList = new java.util.ArrayList<>(List.of());

        for (int i = 0;i<keyIdList.size();i++) {
            System.out.println(keyIdList.get(i));
            Product product = productRepository.findByKeyId(keyIdList.get(i))
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"product codeProduct not found"));

            productList.add(product);
            System.out.println("is not work");
        }
        Oder oder= orderMapper.createOder(orderCreate);
        oder.setCodeOrder(UUID.randomUUID().toString());
        oder.setCustomer(customer);
        oder.setPayment(payment);
        oder.setShipper(shipper);
        oder.setProducts(productList);
        oder.setOrderDate(LocalDate.now());
        oder.setIsDeleted(false);
        oder=orderRepository.save(oder);
        return orderMapper.entityToResponse(oder);
    }

    @Override
    public OrderResponse getOrder(String codeOrder) {
        Oder oder=orderRepository.findByCodeOrder(codeOrder)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"order code not found "));
       oder= orderRepository.save(oder);
        return orderMapper.entityToResponse(oder);
    }

    @Override
    public OrderResponse deleteOrder(String codeOrder) {
        Oder oder=orderRepository.findByCodeOrder(codeOrder)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"order code not found "));
        oder.setIsDeleted(true);
        orderRepository.save(oder);
        return orderMapper.entityToResponse(oder);
    }
    @Override
    public List<OrderResponse> getOrders() {
        List<Oder> oderList = orderRepository.findAll(Sort.by(Sort.Direction.DESC,"oderId"));
        return orderMapper.entityToResponseList(oderList);
    }

    @Override
    public void deleteByCodeOrder(String codeOrder) {
        Oder oder=orderRepository.findByCodeOrder(codeOrder)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"order code not found "));
        orderRepository.delete(oder);
    }
}
