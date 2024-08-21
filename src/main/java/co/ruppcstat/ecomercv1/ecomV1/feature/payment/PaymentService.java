package co.ruppcstat.ecomercv1.ecomV1.feature.payment;

import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentCreate paymentCreate);
    PaymentResponse getPayment(String paymentId);
    PaymentResponse isDeletedPayment(String paymentId);
    Page<PaymentResponse> getAllPayments(int pageNumber, int pageSize);
    void deletePayment(String paymentId);

}
