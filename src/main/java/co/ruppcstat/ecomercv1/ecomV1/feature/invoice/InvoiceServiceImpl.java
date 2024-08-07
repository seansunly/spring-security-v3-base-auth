package co.ruppcstat.ecomercv1.ecomV1.feature.invoice;

import co.ruppcstat.ecomercv1.ecomV1.deman.Customer;
import co.ruppcstat.ecomercv1.ecomV1.deman.Invoice;
import co.ruppcstat.ecomercv1.ecomV1.deman.Product;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.CustomerRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice.InvoiceCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.invoice.dtoInvoice.InvoiceResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.ProductRepository;
import co.ruppcstat.ecomercv1.ecomV1.mapper.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final InvoiceMapper invoiceMapper;
    @Override
    public InvoiceResponse createInvoice(InvoiceCreate invoiceCreate) {
        Customer customerSet=customerRepository.findByPhone(invoiceCreate.phoneCustomer())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number customer not exist"));
        List<String> keyIdList = invoiceCreate.codeProduct();
        List<Product> productList = new java.util.ArrayList<>(List.of());

        for (int i = 0;i<keyIdList.size();i++) {
            System.out.println(keyIdList.get(i));
            Product product = productRepository.findByKeyId(keyIdList.get(i))
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"product codeProduct not found"));

            productList.add(product);
            System.out.println("is not work");
        }

        Invoice invoice= invoiceMapper.create(invoiceCreate);

        invoice.setProducts(productList);
        invoice.setCustomer(customerSet);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setIsDeleted(false);
        invoice.setCodeInvoice(UUID.randomUUID().toString());
        invoice= invoiceRepository.save(invoice);
        return invoiceMapper.entityToResponse(invoice);
    }

    @Override
    public InvoiceResponse getInvoice(String codeInvoice) {
        Invoice invoice=invoiceRepository.findByCodeInvoice(codeInvoice)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invoice code not exist"));
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.entityToResponse(invoice);
    }

    @Override
    public List<InvoiceResponse> getAllInvoice() {
        List<Invoice> invoices=invoiceRepository.findAll(Sort.by(Sort.Direction.DESC,"invoiceId"));
        return invoiceMapper.entityToResponseList(invoices);
    }

    @Override
    public InvoiceResponse isDeletedInvoice(String codeInvoice) {
        Invoice invoice=invoiceRepository.findByCodeInvoice(codeInvoice)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invoice code not exist"));
        invoice.setIsDeleted(true);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.entityToResponse(invoice);
    }
}
