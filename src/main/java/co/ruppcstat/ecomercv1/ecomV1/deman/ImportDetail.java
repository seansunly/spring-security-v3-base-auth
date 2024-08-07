package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "importDetails")
public class ImportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer importDetailID;
    private Integer quantity;
    private String description;
    private Integer unitPrice;
    private Integer amount;
    private Boolean isDeleted;
    //ProductID
    //importId
}
