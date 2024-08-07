package co.ruppcstat.ecomercv1.ecomV1.mapper;


import co.ruppcstat.ecomercv1.ecomV1.deman.Staff;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    Staff createStaff(StaffCreate staffCreate);
    StaffResponse EntityToResponse(Staff staff);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStaff(@MappingTarget Staff staff, StaffUpdate staffUpdate);
    List<StaffResponse> EntityToResponseList(List<Staff> staffList);
}
