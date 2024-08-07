package co.ruppcstat.ecomercv1.ecomV1.feature.shipper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Shipper;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperUpdate;
import co.ruppcstat.ecomercv1.ecomV1.mapper.ShipperMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ShipperServiceImpl implements ShipperService {
    private final ShipperRepository shipperRepository;
    private final ShipperMapper shipperMapper;
    @Override
    public ShipperResponse createShipper(ShipperCreate shipperCreate) {
        if(shipperRepository.existsByContactPhone(shipperCreate.contactPhone())){
            throw new RuntimeException("Contact phone already exists");
        }
        Shipper shipper=shipperMapper.createToShipper(shipperCreate);
        shipper.setIsDeleted(false);
       shipper= shipperRepository.save(shipper);
        return shipperMapper.entityToResponse(shipper);
    }

    @Override
    public ShipperResponse updateShipper(String contactPhone, ShipperUpdate shipperUpdate) {
        Shipper shipper=shipperRepository.findByContactPhone(contactPhone).orElseThrow(()->new RuntimeException("Contact phone not found"));
        shipperMapper.updateShipper(shipper, shipperUpdate);
        shipper=shipperRepository.save(shipper);
        return shipperMapper.entityToResponse(shipper);
    }

    @Override
    public void deleteShipper(String contactPhone) {
        Shipper shipper=shipperRepository.findByContactPhone(contactPhone).orElseThrow(()->new RuntimeException("Contact phone not found"));
        shipperRepository.delete(shipper);
    }

    @Override
    public ShipperResponse getShipper(String contactPhone) {
        Shipper shipper=shipperRepository.findByContactPhone(contactPhone).orElseThrow(()->new RuntimeException("Contact phone not found"));
        shipper=shipperRepository.save(shipper);
        return shipperMapper.entityToResponse(shipper);
    }

    @Override
    public List<ShipperResponse> getShippers() {
        List<Shipper> shippers=shipperRepository.findAll(Sort.by(Sort.Direction.DESC, "shipperId"));
        return shipperMapper.entityListToResponseList(shippers);
    }

    @Override
    public ShipperResponse isDeletedShipper(String contactPhone) {
        Shipper shipper=shipperRepository.findByContactPhone(contactPhone).orElseThrow(()->new RuntimeException("Contact phone not found"));
        shipper.setIsDeleted(true);
        shipper=shipperRepository.save(shipper);
        return shipperMapper.entityToResponse(shipper);
    }
}
