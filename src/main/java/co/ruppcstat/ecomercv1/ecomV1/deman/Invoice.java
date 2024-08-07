package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Setter
@Getter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceId;
    @Column(nullable = false,unique = true)
    private String codeInvoice;
    private LocalDate invoiceDate;
    private Float totalAmount;
    private Boolean isDeleted;

    //CustomerID
    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "invocie_product",
        joinColumns = @JoinColumn(name = "invocie_id",referencedColumnName = "invoiceId"),
        inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "productID"))
    private List<Product> products;
}
