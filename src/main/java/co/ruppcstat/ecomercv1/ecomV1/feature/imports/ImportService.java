package co.ruppcstat.ecomercv1.ecomV1.feature.imports;

import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportUpdate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ImportService {
    ImportResponse createImport(ImportCreate importCreate);
    ImportResponse updateImport( String codeNumberIn,ImportUpdate importUpdate);
    void deleteImport(String codeNumberIn);
    ImportResponse getImport(String codeNumberIn);
    Page<ImportResponse> getImports(int pageNumber, int pageSize);
    ImportResponse importIsDeleted(String codeNumberIn);
}
