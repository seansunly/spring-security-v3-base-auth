package co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale;

import co.ruppcstat.ecomercv1.ecomV1.deman.Product;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.dtoProduct.ProductResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffResponse;

import java.time.LocalDate;
import java.util.List;

public record SaleResponse(
        String codeSale,
        LocalDate saleDate,
        Float totalAmount,
        Boolean isDeleted,
        List<ProductResponse> product,
        StaffResponse staff
) {
}
