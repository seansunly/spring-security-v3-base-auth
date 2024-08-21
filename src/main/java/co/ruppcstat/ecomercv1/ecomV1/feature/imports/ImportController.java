package co.ruppcstat.ecomercv1.ecomV1.feature.imports;

import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/imports")
@RequiredArgsConstructor
public class ImportController {
    private final ImportService importService;
    @GetMapping
    public Page<ImportResponse> getAllImports(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return importService.getImports(pageNumber,pageSize);
    }
    @PostMapping
    public ImportResponse createImport(@Valid @RequestBody ImportCreate importCreate) {
        return importService.createImport(importCreate);
    }

    @GetMapping("/{codeNumber}")
    public ImportResponse getImportByCodeNumber(@PathVariable String codeNumber) {
        return importService.getImport(codeNumber);
    }
    @PatchMapping("/{codeNumber}")
    public ImportResponse updateImport(@PathVariable String codeNumber, @Valid @RequestBody ImportUpdate importUpdate) {
        return importService.updateImport(codeNumber, importUpdate);
    }
    @DeleteMapping("/{codeNumber}")
    public void deleteImport(@PathVariable String codeNumber) {
        importService.deleteImport(codeNumber);
    }
    @PatchMapping("/{codeNumber}/importIsDeleted")
    public ImportResponse isDeletedImport(@PathVariable String codeNumber) {
        return importService.importIsDeleted(codeNumber);
    }
}
