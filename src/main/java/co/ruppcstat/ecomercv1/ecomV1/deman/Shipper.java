package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shippers")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipperId;
    private String name;
    @Column(nullable = false,unique = true)
    private String contactPhone;
    private String contactAddress;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "shipper")
    private List<Oder> orders;

}
