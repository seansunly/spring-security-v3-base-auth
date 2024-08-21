package co.ruppcstat.ecomercv1.ecomV1.feature.userANDrole;

import co.ruppcstat.ecomercv1.ecomV1.deman.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findByPhoneAndIsDeletedFalse(String phoneNumber);
    Boolean existsByPhone(String phone);
    Optional<User> findByPassword(String password);
    Boolean existsByPassword(String password);

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByPhone(String phone);
}
