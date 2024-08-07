package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Supplier;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.supplierDTO.SupplierUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier createSupplier(SupplierCreate supplierCreate);
    SupplierResponse entityToResponse(Supplier supplier);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSupplier(@MappingTarget Supplier supplier, SupplierUpdate supplierUpdate);
    List<SupplierResponse> entityListToResponseList(List<Supplier> supplierList);

}
