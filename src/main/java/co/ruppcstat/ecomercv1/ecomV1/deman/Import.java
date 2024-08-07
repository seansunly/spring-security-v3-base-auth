package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "imports")
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer importId;
    @Column(nullable = false,unique = true)
    private String codeNumber;
    private String importDate;
    private Float totalAmount;
    private Boolean isDeleted;

    @ManyToOne
    private Supplier supplier;
    //SupplierID
    @ManyToOne
    private Staff staff;
    //StaffID
}
