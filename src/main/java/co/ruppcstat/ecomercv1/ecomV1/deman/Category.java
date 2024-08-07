package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categorys")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "category")
    private List<Product> product;

}
