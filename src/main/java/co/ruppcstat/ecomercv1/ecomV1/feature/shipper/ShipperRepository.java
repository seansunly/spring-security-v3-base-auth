package co.ruppcstat.ecomercv1.ecomV1.feature.shipper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShipperRepository extends JpaRepository<Shipper, Integer> {
    Optional<Shipper> findByContactPhone(String contactPhone);
    Boolean existsByContactPhone(String contactPhone);
}
