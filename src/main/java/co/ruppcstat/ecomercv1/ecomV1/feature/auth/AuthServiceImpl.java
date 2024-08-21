package co.ruppcstat.ecomercv1.ecomV1.feature.auth;

import co.ruppcstat.ecomercv1.ecomV1.deman.Role;
import co.ruppcstat.ecomercv1.ecomV1.deman.User;
import co.ruppcstat.ecomercv1.ecomV1.deman.UserVerification;
import co.ruppcstat.ecomercv1.ecomV1.feature.auth.dtoAuth.*;
import co.ruppcstat.ecomercv1.ecomV1.feature.userANDrole.RoleRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.userANDrole.UserRepository;
import co.ruppcstat.ecomercv1.ecomV1.mapper.UserMapper;
import co.ruppcstat.ecomercv1.ecomV1.util.RandomUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JavaMailSender javaMailSender;
    private final UserVerificationRepository userVerificationRepository;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final JwtEncoder refreshTokenJwtEncoder;

    private final JwtEncoder accessTokenJwtEncoder;

    private UserVerification userVerification;
    @Value("${spring.mail.username}")
    private String adminEmail;


    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.refreshToken();

        // Authenticate client with refresh token
        Authentication auth = new BearerTokenAuthenticationToken(refreshToken);
        auth = jwtAuthenticationProvider.authenticate(auth);

        log.info("Auth: {}", auth.getPrincipal());

        Jwt jwt = (Jwt) auth.getPrincipal();
        Instant now = Instant.now();
        JwtClaimsSet jwtAccessClaimsSet = JwtClaimsSet.builder()
                .id(jwt.getId())
                .subject("Access APIs")
                .issuer(jwt.getId())
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.DAYS))
                .audience(jwt.getAudience())
                .claim("isAdmin", true)
                .claim("studentId", "ISTAD0010")
                .claim("scope", jwt.getClaimAsString("scope"))
                .build();

        String accessToken = accessTokenJwtEncoder
                .encode(JwtEncoderParameters.from(jwtAccessClaimsSet))
                .getTokenValue();

        // Get expiration of refresh token
        Instant expiresAt = jwt.getExpiresAt();
        long remainingDays = Duration.between(now, expiresAt).toDays();
        log.info("remainingDays: {}", remainingDays);
        if (remainingDays <= 1) {
            JwtClaimsSet jwtRefreshClaimsSet = JwtClaimsSet.builder()
                    .id(auth.getName())
                    .subject("Refresh Token")
                    .issuer(auth.getName())
                    .issuedAt(now)
                    .expiresAt(now.plus(100, ChronoUnit.DAYS))
                    .audience(List.of("NextJS", "Android", "iOS"))
                    .claim("scope", jwt.getClaimAsString("scope"))
                    .build();
            refreshToken = refreshTokenJwtEncoder
                    .encode(JwtEncoderParameters.from(jwtRefreshClaimsSet))
                    .getTokenValue();
        }


        return AuthResponse.builder()
                .tokenType("Bearer Token")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        log.info("accessToken {}",accessTokenJwtEncoder);
        log.info("refreshToken {}",refreshTokenJwtEncoder);
        //authentication userNamePassword with client (phoneNumber password)

      Authentication auth=new  UsernamePasswordAuthenticationToken(loginRequest.phoneNumber(),loginRequest.password());
      auth=daoAuthenticationProvider.authenticate(auth);
      auth.getAuthorities().forEach(grantedAuthority -> log.info("Authorities :{} ",grantedAuthority.getAuthority()));

      log.info("authenticated user: {}",auth.getPrincipal());
      log.info("authenticated user :{}",auth.getAuthorities());

         //ROLE_USER ROLE_ADMIN
        String scope = auth
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        log.info("Scope: {}", scope);

        // Generate JWT token by JwtEncoder
        // 1. Define JwtClaimsSet (Payload)

        Instant now=Instant.now();
        JwtClaimsSet jwtAccessTokenClaimsSet=JwtClaimsSet.builder()
                .id(auth.getName())
                .subject("Access APIs")
                .issuer(auth.getName())
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.DAYS))
                .audience(List.of("react js","android"))
                .claim("sunly is so handsome",true)
                .claim("StudentId","ISTAD 001")
                .claim("scope",scope)
                .build();

        //
        JwtClaimsSet jwtRefreshTokenClaimsSet=JwtClaimsSet.builder()
                .id(auth.getName())
                .subject("Refresh Token")
                .issuer(auth.getName())
                .issuedAt(now)
                .expiresAt(now.plus(100, ChronoUnit.DAYS))
                .audience(List.of("NextJS", "Android", "iOS"))
                .claim("scope", scope)
                .build();


        //Generate token is string
        String accessToken=accessTokenJwtEncoder
                .encode(JwtEncoderParameters.from(jwtAccessTokenClaimsSet))
                .getTokenValue();
        log.info("access token: {}",accessToken);


        String refreshToken=refreshTokenJwtEncoder
                .encode(JwtEncoderParameters.from(jwtRefreshTokenClaimsSet))
                .getTokenValue();
        log.info("refresh token: {}",refreshToken);
        return AuthResponse.builder()
                .tokenType("Bearer Token")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public RegisterResponse registerShipper(RegisterCreate registerCreate) {
        if(userRepository.existsByPhone(registerCreate.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"phone number already exist");
        }
        if(userRepository.existsByEmail(registerCreate.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email already exist");
        }
        if (userRepository.existsByPassword(registerCreate.password())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"password already exist");
        }
        if(!registerCreate.acceptTerm()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must accept the term");
        }
        if(!registerCreate.password().equals(registerCreate.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password confirmation does not match");
        }
        User user=userMapper.registerToUser(registerCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsDeleted(false);
        user.setIsBlock(false);

        Role roleUser=roleRepository.findByRoleUser();
        log.info("Role roleUser : {}", roleUser.getRoleName());
        Role roleShipper=roleRepository.findByRoleShipper();
        log.info("Role roleShipper : {}", roleShipper.getRoleName());

        List<Role> roles=List.of(roleShipper,roleUser);

        user.setRoles(roles);
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("You have registered successfully, please verify an email!")
                .email(user.getEmail())
                .build();
    }
    @Override
    public RegisterResponse registerEditor(RegisterCreate registerCreate) {
        if(userRepository.existsByPhone(registerCreate.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"phone number already exist");
        }
        if(userRepository.existsByEmail(registerCreate.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email already exist");
        }
        if (userRepository.existsByPassword(registerCreate.password())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"password already exist");
        }
        if(!registerCreate.acceptTerm()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must accept the term");
        }
        if(!registerCreate.password().equals(registerCreate.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password confirmation does not match");
        }
        User user=userMapper.registerToUser(registerCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsDeleted(false);
        user.setIsBlock(false);

        Role roleUser=roleRepository.findByRoleUser();
        log.info("Role roleUser : {}", roleUser.getRoleName());
        Role roleEditor=roleRepository.findByRoleEditor();
        log.info("Role roleEditor : {}", roleEditor.getRoleName());

        List<Role> roles=List.of(roleEditor,roleUser);

        user.setRoles(roles);
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("You have registered successfully, please verify an email!")
                .email(user.getEmail())
                .build();
    }
    @Override
    public RegisterResponse registerAdmin(RegisterCreate registerCreate) {
        if(userRepository.existsByPhone(registerCreate.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"phone number already exist");
        }
        if(userRepository.existsByEmail(registerCreate.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email already exist");
        }
        if (userRepository.existsByPassword(registerCreate.password())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"password already exist");
        }
        if(!registerCreate.acceptTerm()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must accept the term");
        }
        if(!registerCreate.password().equals(registerCreate.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password confirmation does not match");
        }
        User user=userMapper.registerToUser(registerCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsDeleted(false);
        user.setIsBlock(false);

        Role roleUser=roleRepository.findByRoleUser();
        log.info("Role roleUser : {}", roleUser.getRoleName());
        Role roleAdmin=roleRepository.findByRoleAdmin();
        log.info("Role roleAdmin : {}", roleAdmin.getRoleName());

        List<Role> roles=List.of(roleAdmin,roleUser);

        user.setRoles(roles);
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("You have registered successfully, please verify an email!")
                .email(user.getEmail())
                .build();
    }
    @Override
    public RegisterResponse registerSupplier(RegisterCreate registerCreate) {
        if(userRepository.existsByPhone(registerCreate.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"phone number already exist");
        }
        if(userRepository.existsByEmail(registerCreate.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email already exist");
        }
        if (userRepository.existsByPassword(registerCreate.password())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"password already exist");
        }
        if(!registerCreate.acceptTerm()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must accept the term");
        }
        if(!registerCreate.password().equals(registerCreate.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password confirmation does not match");
        }
        User user=userMapper.registerToUser(registerCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsDeleted(false);
        user.setIsBlock(false);

        Role roleUser=roleRepository.findByRoleUser();
        log.info("Role roleUser : {}", roleUser.getRoleName());
        Role roleSupplier=roleRepository.findByRoleCustomer();
        log.info("Role roleSupplier : {}", roleSupplier.getRoleName());

        List<Role> roles=List.of(roleSupplier,roleUser);

        user.setRoles(roles);
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("You have registered successfully, please verify an email!")
                .email(user.getEmail())
                .build();
    }
    @Override
    public RegisterResponse registerCustomer(RegisterCreate registerCreate) {
        if(userRepository.existsByPhone(registerCreate.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"phone number already exist");
        }
        if(userRepository.existsByEmail(registerCreate.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email already exist");
        }
        if (userRepository.existsByPassword(registerCreate.password())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"password already exist");
        }
        if(!registerCreate.acceptTerm()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must accept the term");
        }
        if(!registerCreate.password().equals(registerCreate.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password confirmation does not match");
        }
        User user=userMapper.registerToUser(registerCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsDeleted(false);
        user.setIsBlock(false);

        Role roleUser=roleRepository.findByRoleUser();
        log.info("Role User : {}", roleUser.getRoleName());
        Role roleCustomer=roleRepository.findByRoleCustomer();
        log.info("Role Customer : {}", roleCustomer.getRoleName());


        List<Role> roles=List.of(roleCustomer,roleUser);

        user.setRoles(roles);
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("You have registered successfully, please verify an email!")
                .email(user.getEmail())
                .build();
    }
    @Override
    public RegisterResponse registerStaff(RegisterCreate registerCreate) {
        if(userRepository.existsByPhone(registerCreate.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"phone number already exist");
        }
        if(userRepository.existsByEmail(registerCreate.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email already exist");
        }
        if(!registerCreate.acceptTerm()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You must accept the term");
        }
        if(!registerCreate.password().equals(registerCreate.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password confirmation does not match");
        }

        User user=userMapper.registerToUser(registerCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsDeleted(false);
        user.setIsBlock(false);

        Role roleUser=roleRepository.findByRoleUser();
        Role roleStaff=roleRepository.findByRoleStaff();

        List<Role> roles=List.of(roleStaff,roleUser);
        user.setRoles(roles);
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("You have registered successfully, please verify an email!")
                .email(user.getEmail())
                .build();
    }


    @Override
    public void sendVerificationEmail(String email) throws MessagingException {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Email not found"));
//        if(!userVerificationRepository.existsById(userVerification.getUserVerificationId())){
//            throw new ResponseStatusException(HttpStatus.CONFLICT,"code already exist");
//        }

        UserVerification userVerification=new UserVerification();
        userVerification.setUser(user);
        userVerification.setVerifiedCode(RandomUtil.random6Digits());
        userVerification.setExpiryTime(LocalTime.now().plusMinutes(1));
        userVerificationRepository.save(userVerification);

        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setFrom(adminEmail);
        helper.setSubject("user send Verification email");
        helper.setText(userVerification.getVerifiedCode());
        javaMailSender.send(message);
    }

    @Override
    public void verifyEmail(VerificationRequest verificationRequest) {
        User user=userRepository.findByEmail(verificationRequest.email())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Email not found"));

        UserVerification userVerification=userVerificationRepository.findByUserAndVerifiedCode(user, verificationRequest.verifiedCode())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"user and code not found"));

        if(LocalTime.now().isAfter(userVerification.getExpiryTime())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"userVerification code has expired");

        }


        user.setIsVerified(true);
        user.setIsDeleted(false);
        userRepository.save(user);

        userVerificationRepository.delete(userVerification);
    }

    @Override
    public void reSendVerificationEmail(String email) throws MessagingException {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Email not found"));

        //UserVerification userVerification=new UserVerification();
        UserVerification userVerification=userVerificationRepository.findByUser(user)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found"));

       // userVerification.setUser(user);
        userVerification.setVerifiedCode(RandomUtil.random6Digits());
        userVerification.setExpiryTime(LocalTime.now().plusMinutes(1));
        userVerificationRepository.save(userVerification);

        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setFrom(adminEmail);
        helper.setSubject("user send Verification email");
        helper.setText(userVerification.getVerifiedCode());
        javaMailSender.send(message);
    }

    @Override
    public void changPassword(ChangPassword changPassword) {
        User user=userRepository.findByPassword(changPassword.password())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Email and Code not found"));

        if(userRepository.existsByEmail(changPassword.email())){
            if(!changPassword.newPassword().equals(changPassword.confirmNewPassword())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password confirmation does not match");
            }

            userMapper.changPassword1(user,changPassword);
            user.setPassword(passwordEncoder.encode(changPassword.newPassword()));
            userRepository.save(user);
        }


    }
}
