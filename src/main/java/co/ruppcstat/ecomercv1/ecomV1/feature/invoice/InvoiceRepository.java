package co.ruppcstat.ecomercv1.ecomV1.feature.invoice;

import co.ruppcstat.ecomercv1.ecomV1.deman.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findByCodeInvoice(String codeInvoice);
    Boolean existsByCodeInvoice(String codeInvoice);
}
