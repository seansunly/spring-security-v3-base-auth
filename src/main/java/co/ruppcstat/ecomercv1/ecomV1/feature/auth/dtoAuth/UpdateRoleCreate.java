package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import co.ruppcstat.ecomercv1.ecomV1.deman.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateRoleCreate(
        @NotBlank(message = "role name is request")
        List<String> roleName,
        @NotBlank(message = "email is request")
        String email
) {
}
