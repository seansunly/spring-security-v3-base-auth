package co.ruppcstat.ecomercv1.ecomV1.feature.imports;

import co.ruppcstat.ecomercv1.ecomV1.deman.Import;
import co.ruppcstat.ecomercv1.ecomV1.deman.Staff;
import co.ruppcstat.ecomercv1.ecomV1.deman.Supplier;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportUpdate;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.SupplierRepository;
import co.ruppcstat.ecomercv1.ecomV1.mapper.ImportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {
   private final ImportRepository importRepository;
   private final StaffRepository staffRepository;
   private final SupplierRepository supplierRepository;
   private final ImportMapper importMapper;
    @Override
    public ImportResponse createImport(ImportCreate importCreate) {

        Staff staff=staffRepository.findByPhone(importCreate.phone())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"phone number staff not found"));
        Supplier supplier=supplierRepository.findByContactPhone(importCreate.contactPhone())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact phone supplier not found"));

        Import imports=importMapper.createImport(importCreate);
        imports.setIsDeleted(false);
        imports.setCodeNumber(UUID.randomUUID().toString());
        imports.setStaff(staff);
        imports.setSupplier(supplier);
        imports.setImportDate(LocalDate.now().toString());
        imports= importRepository.save(imports);
        return importMapper.entityToDto(imports);
    }

    @Override
    public ImportResponse updateImport(String codeNumberIn, ImportUpdate importUpdate) {
        Import imports=importRepository.findByCodeNumber(codeNumberIn).orElseThrow(()
                -> new ResponseStatusException( HttpStatus.NOT_FOUND,"Code number not found"));
        importMapper.updateImport(imports, importUpdate);
       imports= importRepository.save(imports);
        return importMapper.entityToDto(imports);
    }

    @Override
    public void deleteImport(String codeNumberIn) {
        Import imports=importRepository.findByCodeNumber(codeNumberIn)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Code number not found"));
        importRepository.delete(imports);

    }

    @Override
    public ImportResponse getImport(String codeNumberIn) {
        Import imports=importRepository.findByCodeNumber(codeNumberIn)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Code number not found"));
       imports= importRepository.save(imports);
        return importMapper.entityToDto(imports);
    }

    @Override
    public Page<ImportResponse> getImports(int pageNumber, int pageSize) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "importId");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Import> imports=importRepository.findAll(pageRequest);
        return imports.map(importMapper::entityToDto);
    }

    @Override
    public ImportResponse importIsDeleted(String codeNumberIn) {
        Import imports=importRepository.findByCodeNumber(codeNumberIn)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Code number not found"));
        imports.setIsDeleted(true);
       imports= importRepository.save(imports);
        return importMapper.entityToDto(imports);
    }
}
