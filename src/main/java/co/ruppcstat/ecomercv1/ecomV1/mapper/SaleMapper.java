package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Sale;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleUpdate;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {


    Sale toSaleResponse(SaleCreate saleCreate);
    @Mapping(source = "staffId", target = "staff.staffId")
    Sale createSale(SaleCreate saleCreate);

    @Mapping(source = "sale.products", target = "product")
    SaleResponse entityToResponse(Sale sale);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSale(@MappingTarget Sale sale, SaleUpdate saleUpdate);
    List<SaleResponse> entityListToResponseList(List<Sale> sales);
}
