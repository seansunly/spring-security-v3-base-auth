package co.ruppcstat.ecomercv1.ecomV1.feature.customer;

import co.ruppcstat.ecomercv1.ecomV1.deman.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByCustomerID(Integer customerId);
    Optional<Customer> findByPhone(String phoneNumber);
    Boolean existsByPhone(String phoneNumber);
    Optional<Customer> findByEmail(String email);
    Boolean existsByEmail(String email);
}
