package co.ruppcstat.ecomercv1.ecomV1.feature.supplier;

import co.ruppcstat.ecomercv1.ecomV1.deman.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Optional<Supplier> findBySupplierID(Integer supplierID);
    Optional<Supplier> findByContactPhone(String contactPhone);
    Boolean existsByContactPhone(String contactPhone);

}
