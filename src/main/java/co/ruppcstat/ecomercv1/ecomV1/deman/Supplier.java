package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierID;
    private String name;
    @Column(unique = true,nullable = false)
    private String contactPhone;
    private String contactAddress;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "supplier")
    private List<Import> importList;
}
