package co.ruppcstat.ecomercv1.ecomV1.feature.order;

import co.ruppcstat.ecomercv1.ecomV1.deman.Oder;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface OrderRepository extends JpaRepository<Oder, Integer> {
    Optional<Oder> findByCodeOrder(String codeOrder);
    Boolean existsByCodeOrder(String codeOrder);
}
