package co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale;

import co.ruppcstat.ecomercv1.ecomV1.deman.Product;

import java.time.LocalDate;
import java.util.List;

public record SaleCreate(
        Float totalAmount,
        Integer staffId,
        List<String> keyId
) {
}
