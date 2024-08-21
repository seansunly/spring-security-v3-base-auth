package co.ruppcstat.ecomercv1.ecomV1.feature.shipper;

import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperUpdate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShipperService {
    ShipperResponse createShipper(ShipperCreate shipperCreate);
    ShipperResponse updateShipper(String contactPhone,ShipperUpdate shipperUpdate);
    void deleteShipper(String contactPhone);
    ShipperResponse getShipper(String contactPhone);
    Page<ShipperResponse> getShippers(int pageNumber, int pageSize);
    ShipperResponse isDeletedShipper(String contactPhone);

}
