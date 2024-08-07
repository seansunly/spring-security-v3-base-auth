package co.ruppcstat.ecomercv1.ecomV1.feature.sale;

import co.ruppcstat.ecomercv1.ecomV1.deman.Sale;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleUpdate;

import java.util.List;

public interface SalService {
    SaleResponse createSale(SaleCreate saleCreate);
    SaleResponse updateSale(String codeSale,SaleUpdate saleUpdate);
    void deleteSale(String codeSale);
    SaleResponse getSale(String codeSale);
    List<SaleResponse> getSales();
    SaleResponse isDeletedSale(String codeSale);
}
