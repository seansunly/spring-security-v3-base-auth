package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "oderDetails")
public class OderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oderDetailId;
    private Float unitPrice;
    private Integer quantity;
    private Float discount;
    private Boolean isDeleted;
    //InvoiceID
    //ProductID
}
