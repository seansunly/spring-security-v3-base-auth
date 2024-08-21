package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ChangPassword(
        @NotBlank(message = "email is request")
        String email,
        @NotBlank(message = "old password is request for valid with new password")
        String password,

        @NotBlank(message = "new password is request for create password")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                message = "Password must contain minimum 8 characters in length, " +
                        "at least one uppercase English letter, " +
                        "at least one lowercase English letter, " +
                        "at least one digit," +
                        "at least one special character.") // Regular Expression
        String newPassword,
        @NotBlank(message = "confirmNewPassword is request for new password")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                message = "Password must contain minimum 8 characters in length, " +
                        "at least one uppercase English letter, " +
                        "at least one lowercase English letter, " +
                        "at least one digit," +
                        "at least one special character.") // Regular Expression
        String confirmNewPassword
) {
}
