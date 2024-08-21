package co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterCreate(
        @NotBlank(message = "userName is required")
         String userName,
         @NotBlank(message = "password is required")
         @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                 message = "Password must contain minimum 8 characters in length, " +
                         "at least one uppercase English letter, " +
                         "at least one lowercase English letter, " +
                         "at least one digit," +
                         "at least one special character.") // Regular Expression
         String password,
         @NotBlank(message = "confirmPassword is required")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                message = "Password must contain minimum 8 characters in length, " +
                        "at least one uppercase English letter, " +
                        "at least one lowercase English letter, " +
                        "at least one digit," +
                        "at least one special character.") // Regular Expression
         String confirmPassword,
         @NotBlank(message = "email is required")
         String email,
         @NotBlank(message = "phone number is required")
         @Size(min = 9,max = 15,message = "phone number must be 9 up")
         String phone,
         @NotBlank(message = "gender is required")
         String gender,
         @NotNull(message = "acceptTerm is required")
         Boolean acceptTerm
) {
}
