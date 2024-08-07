package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @Column(nullable = false,unique = true,length = 20)
    private String codePayment;
    private LocalDate paymentDate;
    private Float paymentAmount;
    private Boolean isDeleted;

    //CustomerID
    @ManyToOne
    private Staff staff;
    //StaffID
    @OneToMany(mappedBy = "payment")
    private List<Oder> orders;
}
