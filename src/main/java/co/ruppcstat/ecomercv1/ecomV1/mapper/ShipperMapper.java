package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Shipper;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipperMapper {

    Shipper createToShipper(ShipperCreate shipperCreate);
    ShipperResponse entityToResponse(Shipper shipper);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShipper(@MappingTarget Shipper shipper, ShipperUpdate shipperUpdate);
    List<ShipperResponse> entityListToResponseList(List<Shipper> shipperList);
}
