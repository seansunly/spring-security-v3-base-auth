package co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale;

import java.time.LocalDate;

public record SaleUpdate(
        String codeSale,
        LocalDate saleDate,
        Float totalAmount,
        Boolean isDeleted
) {
}
