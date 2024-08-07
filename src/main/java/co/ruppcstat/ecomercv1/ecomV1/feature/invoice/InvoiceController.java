package co.ruppcstat.ecomercv1.ecomV1.feature.invoice;

import co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice.InvoiceCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice.InvoiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;
    @PostMapping
    public InvoiceResponse createInvoice(@Valid @RequestBody InvoiceCreate invoiceCreate) {
        return invoiceService.createInvoice(invoiceCreate);
    }
    @GetMapping
    public List<InvoiceResponse> getInvoices() {
        return invoiceService.getAllInvoice();
    }
    @GetMapping("/{codeInvoice}")
    public InvoiceResponse getInvoice(@PathVariable String codeInvoice) {
        return invoiceService.getInvoice(codeInvoice);
    }
    @PatchMapping("/{codeInvoice}")
    public InvoiceResponse updateIsDeleted(@PathVariable String codeInvoice) {
        return invoiceService.isDeletedInvoice(codeInvoice);
    }

}
