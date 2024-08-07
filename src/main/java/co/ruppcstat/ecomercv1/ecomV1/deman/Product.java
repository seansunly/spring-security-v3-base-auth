package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.mail.Folder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;
    @Column(nullable = false,unique = true)
    private String keyId;
    private String image;
    private String name;
    private String description;
    private Float price;
    private Boolean isDeleted;
    @ManyToOne()
//    @JoinTable(name = "category_products",
//        joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "productID"),
//        inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "categoryID"))
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<Sale> sales;

//    @ManyToMany(mappedBy = "products")
//    private List<Sale> sales;
//    @OneToMany(mappedBy = "product")
//    private List<Sales_Products> salesProducts;
    @ManyToMany(mappedBy = "products")
    private List<Oder> orders;
    @ManyToMany(mappedBy = "products")
    private List<Invoice> invoices;


}
