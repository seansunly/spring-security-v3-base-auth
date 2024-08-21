package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import co.ruppcstat.ecomercv1.ecomV1.deman.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record RoleResponse(
        String message,
        String email,
        List<String>  roleName

) {
}
