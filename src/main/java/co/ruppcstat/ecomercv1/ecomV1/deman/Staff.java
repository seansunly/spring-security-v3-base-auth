package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;
    private String image;
    private String nameKH;
    private String nameEN;
    private String gender;
    private LocalDate brithDate;
    @Column(unique = true, nullable = false)
    private String phone;
    private String address;
    private String position;
    private Float salary;
    private LocalDate hiredDate;
    private Boolean stopWork;
    @OneToMany(mappedBy = "staff")
    private List<Import> anImport;
    @OneToMany(mappedBy = "staff")
    private List<Sale> sales;

    @OneToMany(mappedBy = "staff")
    private List<Payment> payments;
}
