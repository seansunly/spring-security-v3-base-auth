package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;
    private String name;
    @Column(unique=true,nullable=false)
    private String phone;
    private String address;
    private String type;
    private String email;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "customer")
    private List<Oder> orders;
    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;

}
