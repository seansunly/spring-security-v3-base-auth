package co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice;

import java.util.List;

public record InvoiceCreate(
        Float totalAmount,
        String phoneCustomer,
        List<String> codeProduct


) {
}
