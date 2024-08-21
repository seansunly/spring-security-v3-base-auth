package co.ruppcstat.ecomercv1.ecomV1.feature.auth;

import co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth.*;
import jakarta.mail.MessagingException;

public interface AuthService {

    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    AuthResponse login(LoginRequest loginRequest);
    //register
    RegisterResponse registerShipper(RegisterCreate registerCreate);
    RegisterResponse registerEditor(RegisterCreate registerCreate);
    RegisterResponse registerAdmin(RegisterCreate registerCreate);
    RegisterResponse registerSupplier (RegisterCreate registerCreate);
    RegisterResponse registerCustomer(RegisterCreate registerCreate);
    RegisterResponse registerStaff(RegisterCreate registerCreate);
    //register

    void sendVerificationEmail(String email)throws MessagingException;

    void verifyEmail(VerificationRequest verificationRequest);
    void reSendVerificationEmail(String email)throws MessagingException;
    void changPassword (ChangPassword changPassword);
}
