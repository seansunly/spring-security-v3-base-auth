package co.ruppcstat.ecomercv1.ecomV1.feature.staff;

import co.ruppcstat.ecomercv1.ecomV1.deman.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Optional<Staff> findByPhone(String phone);
    Boolean existsByPhone(String phone);
    Optional<Staff> findByStaffId(Integer staffId);
}
