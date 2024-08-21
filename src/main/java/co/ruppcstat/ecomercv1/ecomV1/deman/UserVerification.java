package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "userVerifications")
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userVerificationId;
    private String verifiedCode;

    private LocalTime expiryTime;

    @OneToOne
    private User user;
}
