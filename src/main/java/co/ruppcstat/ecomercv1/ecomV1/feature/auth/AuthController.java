package co.ruppcstat.ecomercv1.ecomV1.feature.auth;

import co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth.*;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registerCustomer")
    public RegisterResponse registerCustomer(@Valid @RequestBody RegisterCreate registerCreate) {
        return authService.registerCustomer(registerCreate);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registerStaff")
    public RegisterResponse registerStaff(@Valid @RequestBody RegisterCreate registerCreate) {
        return authService.registerStaff(registerCreate);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registerAdmin")
    public RegisterResponse registerAdmin(@Valid @RequestBody RegisterCreate registerCreate) {
        return authService.registerAdmin(registerCreate);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registerEditor")
    public RegisterResponse registerEditor(@Valid @RequestBody RegisterCreate registerCreate) {
        return authService.registerEditor(registerCreate);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registerShipper")
    public RegisterResponse registerShipper(@Valid @RequestBody RegisterCreate registerCreate) {
        return authService.registerShipper(registerCreate);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registerSupplier")
    public RegisterResponse registerSupplier(@Valid @RequestBody RegisterCreate registerCreate) {
        return authService.registerSupplier(registerCreate);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refreshToken")
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/sen-verification")
    public void sendVerification(@Valid @RequestBody SendVerificationRequest sendVerificationRequest) throws MessagingException {
        authService.sendVerificationEmail(sendVerificationRequest.email());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/verification-email")
    void verifyEmail(@Valid @RequestBody VerificationRequest verificationRequest) {
        authService.verifyEmail(verificationRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/resend-verification")
    public void verifyEmail(@Valid @RequestBody SendVerificationRequest sendVerificationRequest) throws MessagingException {
        authService.reSendVerificationEmail(sendVerificationRequest.email());
    }

    @PatchMapping("/changPassword")
    public void changPassword(@Valid @RequestBody ChangPassword changPassword) {
        authService.changPassword(changPassword);
    }


}
