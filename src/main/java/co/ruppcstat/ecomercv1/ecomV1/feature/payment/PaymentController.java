package co.ruppcstat.ecomercv1.ecomV1.feature.payment;

import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse creatPayment(@Valid @RequestBody PaymentCreate paymentCreate) {
        return paymentService.createPayment(paymentCreate);
    }
    @GetMapping
    public List<PaymentResponse> getPayments() {
        return paymentService.getAllPayments();
    }
    @GetMapping("/{paymentId}")
    public PaymentResponse getPayment(@PathVariable String paymentId) {
        return paymentService.getPayment(paymentId);
    }
    @DeleteMapping("/{paymentId}")
    public void deletePayment(@PathVariable String paymentId) {
        paymentService.isDeletedPayment(paymentId);
    }
    @PatchMapping("/{paymentId}")
    public PaymentResponse updateIsDeletedPayment(@PathVariable String paymentId) {
        return paymentService.isDeletedPayment(paymentId);
    }
}
