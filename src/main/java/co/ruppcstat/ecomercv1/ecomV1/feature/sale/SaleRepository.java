package co.ruppcstat.ecomercv1.ecomV1.feature.sale;

import co.ruppcstat.ecomercv1.ecomV1.deman.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Optional<Sale> findByCodeSale(String codeSale);
    Boolean existsByCodeSale(String codeSale);

}
