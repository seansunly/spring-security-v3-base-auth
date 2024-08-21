package co.ruppcstat.ecomercv1.ecomV1.feature.supplier;

import co.ruppcstat.ecomercv1.ecomV1.deman.Supplier;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierUpdate;
import co.ruppcstat.ecomercv1.ecomV1.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService{
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    @Override
    public SupplierResponse createSupplier(SupplierCreate supplierCreate) {
        if(supplierRepository.existsByContactPhone(supplierCreate.contactPhone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Contact phone already exists");
        }
        Supplier supplier =supplierMapper.createSupplier(supplierCreate);
        supplier.setIsDeleted(false);
        supplierRepository.save(supplier);
        return supplierMapper.entityToResponse(supplier);
    }


    @Override
    public SupplierResponse updateSupplier(String contactPhone, SupplierUpdate supplierUpdate) {
        Supplier supplier=supplierRepository.findByContactPhone(contactPhone)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        supplierMapper.updateSupplier(supplier,supplierUpdate);
        supplier= supplierRepository.save(supplier);
        return supplierMapper.entityToResponse(supplier);
    }

    @Override
    public void deleteSupplier(String contactPhone) {
        Supplier supplier=supplierRepository.findByContactPhone(contactPhone).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        supplierRepository.delete(supplier);
        //supplierRepository.save(supplier);
    }

    @Override
    public SupplierResponse getSupplier(String contactPhone) {
        Supplier supplier=supplierRepository.findByContactPhone(contactPhone).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        return supplierMapper.entityToResponse(supplier);
    }

    @Override
    public Page<SupplierResponse> getAllSuppliers(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "supplierID");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Supplier> suppliers=supplierRepository.findAll(pageRequest);
        return suppliers.map(supplierMapper::entityToResponse);
    }

    @Override
    public SupplierResponse updateDeletedSupplier(String contactPhone) {
        Supplier supplier=supplierRepository.findByContactPhone(contactPhone).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        supplier.setIsDeleted(true);
       supplier= supplierRepository.save(supplier);
        return supplierMapper.entityToResponse(supplier);
    }
}
