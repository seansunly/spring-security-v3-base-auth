package co.ruppcstat.ecomercv1.ecomV1.feature.payment;

import co.ruppcstat.ecomercv1.ecomV1.deman.Payment;
import co.ruppcstat.ecomercv1.ecomV1.deman.Staff;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffRepository;
import co.ruppcstat.ecomercv1.ecomV1.mapper.PaymentMapper;
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
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final StaffRepository staffRepository;
    private final PaymentMapper paymentMapper;
    @Override
    public PaymentResponse createPayment(PaymentCreate paymentCreate) {
        Staff phonStaff=staffRepository.findByPhone(paymentCreate.phoneStaff())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone Not Found"));
        Payment payment = paymentMapper.createPayment(paymentCreate);
        payment.setStaff(phonStaff);
        //payment.setCodePayment(UUID.randomUUID().toString());
        payment.setPaymentDate(LocalDate.now());
        payment.setIsDeleted(false);
        payment= paymentRepository.save(payment);
        return paymentMapper.entityToDTO(payment);
    }

    @Override
    public PaymentResponse getPayment(String paymentId) {
        Payment payment=paymentRepository.findByCodePayment(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment id not found"));
       payment= paymentRepository.save(payment);
        return paymentMapper.entityToDTO(payment);
    }

    @Override
    public PaymentResponse isDeletedPayment(String paymentId) {
        Payment payment=paymentRepository.findByCodePayment(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment id not found"));
        payment.setIsDeleted(true);
       payment= paymentRepository.save(payment);
        return paymentMapper.entityToDTO(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments=paymentRepository.findAll(Sort.by(Sort.Direction.DESC,"paymentId"));
        return paymentMapper.entityToDTO(payments);
    }
}
