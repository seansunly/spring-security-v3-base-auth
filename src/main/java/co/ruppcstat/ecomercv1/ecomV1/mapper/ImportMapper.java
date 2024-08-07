package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Import;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.imports.dtoImport.ImportUpdate;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImportMapper {
    Import createImport(ImportCreate importCreate);
    ImportResponse entityToDto(Import entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateImport(@MappingTarget Import entity, ImportUpdate importUpdate);
    List<ImportResponse> entityToDto(List<Import> entityList);
}
