package co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment;

import java.time.LocalDate;

public record PaymentResponse(
        String codePayment,
        LocalDate paymentDate,
        Float paymentAmount,
        Boolean isDeleted,
        String nameStaff
) {
}
