package co.ruppcstat.ecomercv1.ecomV1.feature.payment.dtoPayment;

public record PaymentCreate(
    String phoneStaff,
    Float paymentAmount,
    String codePayment
) {
}
