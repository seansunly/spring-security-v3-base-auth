package co.ruppcstat.ecomercv1.ecomV1.feature.payment;

import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<PaymentResponse> getPayments(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return paymentService.getAllPayments(pageNumber,pageSize);
    }
    @GetMapping("/{paymentId}")
    public PaymentResponse getPayment(@PathVariable String paymentId) {
        return paymentService.getPayment(paymentId);
    }
    @DeleteMapping("/{paymentId}/isDeleted")
    public PaymentResponse deletePayment(@PathVariable String paymentId) {
        paymentService.isDeletedPayment(paymentId);
        return paymentService.getPayment(paymentId);
    }
    @PatchMapping("/{paymentId}")
    public PaymentResponse updateIsDeletedPayment(@PathVariable String paymentId) {
        return paymentService.isDeletedPayment(paymentId);
    }
    @DeleteMapping("/{paymentId}")
    public void deletedPayment(@PathVariable String paymentId) {
        paymentService.deletePayment(paymentId);
    }
}
