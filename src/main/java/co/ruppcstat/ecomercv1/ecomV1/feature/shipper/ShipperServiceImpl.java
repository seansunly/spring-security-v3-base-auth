package co.ruppcstat.ecomercv1.ecomV1.feature.shipper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Shipper;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.dtoShipper.ShipperUpdate;
import co.ruppcstat.ecomercv1.ecomV1.mapper.ShipperMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ShipperServiceImpl implements ShipperService {
    private final ShipperRepository shipperRepository;
    private final ShipperMapper shipperMapper;
    @Override
    public ShipperResponse createShipper(@RequestBody ShipperCreate shipperCreate) {
        if(shipperRepository.existsByContactPhone(shipperCreate.contactPhone())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Contact phone already exists");
        }
        Shipper shipper=shipperMapper.createToShipper(shipperCreate);
        shipper.setIsDeleted(false);
       shipper= shipperRepository.save(shipper);
        return shipperMapper.entityToResponse(shipper);
    }

    @Override
    public ShipperResponse updateShipper(@Valid String contactPhone,@RequestBody ShipperUpdate shipperUpdate) {
        Shipper shipper=shipperRepository.findByContactPhone(contactPhone).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact phone not found"));
        shipperMapper.updateShipper(shipper, shipperUpdate);
        shipper=shipperRepository.save(shipper);
        return shipperMapper.entityToResponse(shipper);
    }

    @Override
    public void deleteShipper(@Valid @PathVariable String contactPhone) {
        Shipper shipper=shipperRepository.findByContactPhone(contactPhone)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact phone not found"));
        shipperRepository.delete(shipper);
    }

    @Override
    public ShipperResponse getShipper(@Valid @PathVariable String contactPhone) {
        Shipper shipper=shipperRepository.findByContactPhone(contactPhone)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact phone not found"));
        shipper=shipperRepository.save(shipper);
        return shipperMapper.entityToResponse(shipper);
    }

    @Override
    public Page<ShipperResponse> getShippers(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "shipperId");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Shipper> shippers=shipperRepository.findAll(pageRequest);
        return shippers.map(shipperMapper::entityToResponse);
    }

    @Override
    public ShipperResponse isDeletedShipper(@Valid String contactPhone) {
        Shipper shipper=shipperRepository.findByContactPhone(contactPhone).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact phone not found"));
        shipper.setIsDeleted(true);
        shipper=shipperRepository.save(shipper);
        return shipperMapper.entityToResponse(shipper);
    }
}
