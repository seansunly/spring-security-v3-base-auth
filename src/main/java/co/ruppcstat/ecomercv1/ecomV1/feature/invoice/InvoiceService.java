package co.ruppcstat.ecomercv1.ecomV1.feature.invoice;

import co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice.InvoiceCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice.InvoiceResponse;

import java.util.List;

public interface InvoiceService {
    InvoiceResponse createInvoice(InvoiceCreate invoiceCreate);
    InvoiceResponse getInvoice(String codeInvoice);
    List<InvoiceResponse> getAllInvoice();
    InvoiceResponse isDeletedInvoice(String codeInvoice);
}
