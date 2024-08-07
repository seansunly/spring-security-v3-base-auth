package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Payment;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment.PaymentResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "phoneStaff",target = "staff.phone")
    Payment createPayment(PaymentCreate paymentCreate);
    @Mapping(source = "staff.nameEN",target = "nameStaff")
    PaymentResponse entityToDTO(Payment payment);
    List<PaymentResponse> entityToDTO(List<Payment> payments);
}
