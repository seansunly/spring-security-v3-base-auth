package co.ruppcstat.ecomercv1.ecomV1.feature.auth;

import co.ruppcstat.ecomercv1.ecomV1.deman.User;
import co.ruppcstat.ecomercv1.ecomV1.deman.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Integer> {
    Optional<UserVerification> findByUserAndVerifiedCode(User user, String verifiedCode);

    Optional<UserVerification> findByUser(User user);
}
