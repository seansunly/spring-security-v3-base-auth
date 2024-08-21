package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Role;
import co.ruppcstat.ecomercv1.ecomV1.deman.User;
import co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth.ChangPassword;
import co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth.RegisterCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth.RoleResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth.UpdateRoleCreate;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registerToUser(RegisterCreate registerCreate);

    User changPassword(ChangPassword changPassword);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "newPassword",target = "password")
    void changPassword1(@MappingTarget User user, ChangPassword changPassword);

   // RoleResponse updateRoleStaff(UpdateRoleCreate updateRoleCreate);

}
