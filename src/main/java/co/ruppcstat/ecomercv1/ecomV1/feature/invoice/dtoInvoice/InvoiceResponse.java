package co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice;

import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductResponse;

import java.time.LocalDate;
import java.util.List;

public record InvoiceResponse(
        String codeInvoice,
        LocalDate invoiceDate,
        Float totalAmount,
        Boolean isDeleted,
        String phoneCustomer,
        List<ProductResponse> codeProducts

) {
}
