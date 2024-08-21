package co.ruppcstat.ecomercv1.ecomV1.feature.payment;

import co.ruppcstat.ecomercv1.ecomV1.deman.Payment;
import co.ruppcstat.ecomercv1.ecomV1.deman.Staff;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffRepository;
import co.ruppcstat.ecomercv1.ecomV1.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        if(paymentRepository.existsByCodePayment(paymentCreate.codePayment())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Code Payment Already Exists");
        }
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment code not found"));
       payment= paymentRepository.save(payment);
        return paymentMapper.entityToDTO(payment);
    }

    @Override
    public PaymentResponse isDeletedPayment(String paymentId) {
        Payment payment=paymentRepository.findByCodePayment(paymentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Payment code not found"));
        payment.setIsDeleted(true);
       payment= paymentRepository.save(payment);
        return paymentMapper.entityToDTO(payment);
    }

    @Override
    public Page<PaymentResponse> getAllPayments(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "paymentId");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Payment> payments=paymentRepository.findAll(pageRequest);
        return payments.map(paymentMapper::entityToDTO);
    }

    @Override
    public void deletePayment(String paymentId) {
        Payment payment=paymentRepository.findByCodePayment(paymentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Payment code not found"));
        paymentRepository.delete(payment);

    }
}
