package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "oders")
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oderId;
    @Column(nullable = false,unique = true)
    private String codeOrder;
    private LocalDate orderDate;
    private Integer quantity;
    private Boolean isDeleted;
    @ManyToOne
    private Customer customer;
    //CustomerID
    @ManyToOne
    private Payment payment;
    //PaymentID
    @ManyToOne
    private Shipper shipper;
    //ShipperID
    @ManyToMany
    @JoinTable(name = "order_product",
        joinColumns = @JoinColumn(name = "order_id",referencedColumnName = "oderId"),
        inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "productID"))
    private List<Product> products;
}
