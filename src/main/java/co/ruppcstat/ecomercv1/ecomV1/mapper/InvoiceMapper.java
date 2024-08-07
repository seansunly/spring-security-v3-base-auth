package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Invoice;
import co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice.InvoiceCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice.InvoiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(source = "phoneCustomer",target = "customer.phone")
    Invoice create(InvoiceCreate invoiceCreate);
    @Mapping(source = "customer.phone",target = "phoneCustomer")
    @Mapping(source = "products",target = "codeProducts")
    InvoiceResponse entityToResponse(Invoice invoice);
    List<InvoiceResponse> entityToResponseList(List<Invoice> invoices);

}
