package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleId;
    @Column(nullable = false,unique = true)
    private String codeSale;
    private LocalDate saleDate;
    private Float totalAmount;
    private Boolean isDeleted;

    @ManyToOne
    private Staff staff;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sales_products",
    joinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "saleId"),
    inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "productID"))
    private List<Product> products;

//    @OneToMany(mappedBy = "sale")
//    private List<Sales_Products> salesProducts;
//    @ManyToMany
//    private List<Product> products;


//    //ProductID
//    @ManyToOne
//    @JoinTable(name = "sale_staff",
//        joinColumns = @JoinColumn(name = "sale_id",referencedColumnName = "saleId"),
//        inverseJoinColumns = @JoinColumn(name = "staff_id",referencedColumnName = "staffId"))
//    private Staff staff;

    //StaffID
}
