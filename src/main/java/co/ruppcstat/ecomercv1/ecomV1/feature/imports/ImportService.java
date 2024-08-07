package co.ruppcstat.ecomercv1.ecomV1.feature.imports;

import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportUpdate;

import java.util.List;

public interface ImportService {
    ImportResponse createImport(ImportCreate importCreate);
    ImportResponse updateImport( String codeNumberIn,ImportUpdate importUpdate);
    void deleteImport(String codeNumberIn);
    ImportResponse getImport(String codeNumberIn);
    List<ImportResponse> getImports();
    ImportResponse importIsDeleted(String codeNumberIn);
}
