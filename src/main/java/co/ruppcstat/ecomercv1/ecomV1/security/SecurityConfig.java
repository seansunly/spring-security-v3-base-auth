package co.ruppcstat.ecomercv1.ecomV1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    //Bean fo refresh token
    @Bean
    JwtAuthenticationProvider configJwtAuthenticationProvider(@Qualifier("refreshTokenJwtDecoder") JwtDecoder refreshTokenJwtEncoder) {
        return new JwtAuthenticationProvider(refreshTokenJwtEncoder);

    }
    @Bean
    DaoAuthenticationProvider configDaoauthenticationProvider (){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }
   /* //use for any user a lot of user
    @Bean
    InMemoryUserDetailsManager configureUserSecurity(){
        UserDetails admin= User.withUsername("admin")
                //.password("{noop}admin123")
                .password(passwordEncoder.encode("admin123"))
                .roles("USER","ADMIN")
                .build();

        UserDetails editor= User.withUsername("editor")
                //.password("{noop}editor123")
                .password(passwordEncoder.encode("editor123"))
                .roles("USER","EDITOR")
                .build();

        UserDetails customer= User.withUsername("customer")
                //.password("{noop}customer123")
                .password(passwordEncoder.encode("customer123"))
                .roles("USER","CUSTOMER")
                .build();

        UserDetails supplier= User.withUsername("supplier")
                //.password("{noop}supplier123")
                .password(passwordEncoder.encode("supplier123"))
                .roles("USER","SUPPLIER")
                .build();

        UserDetails staff= User.withUsername("staff")
                //.password("{noop}staff123")
                .password(passwordEncoder.encode("staff123"))
                .roles("USER","STAFF")
                .build();

        UserDetails shipper= User.withUsername("shipper")
                //.password("{noop}shipper123")
                .password(passwordEncoder.encode("shipper123"))
                .roles("USER","SHIPPER")
                .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(admin);
        manager.createUser(editor);
        //in table have any roles
        manager.createUser(customer);
        manager.createUser(supplier);
        manager.createUser(staff);
        manager.createUser(shipper);
        return manager;

    }*/

    @Bean
    SecurityFilterChain configureAPISecurity(HttpSecurity http,
                                             @Qualifier("accessTokenJwtDecoder") JwtDecoder jwtDecoder) throws Exception {
        //EndPoint security config
        http.authorizeHttpRequests(endpoint->
                endpoint
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/upload/**","/upload/**").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/api/v1/customers/**").hasAnyAuthority("SCOPE_EDITOR","SCOPE_ADMIN","SCOPE_CUSTOMER")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/customers/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/customers/**").hasAnyAuthority("SCOPE_CUSTOMER")
//                        .requestMatchers(HttpMethod.GET,"/api/v1/customers/**").hasAnyAuthority("SCOPE_CUSTOMER","SCOPE_ADMIN","SCOPE_EDITOR","SCOPE_USER")
//                        //Supplier
//                        .requestMatchers(HttpMethod.GET,"/api/v1/suppliers").hasAnyAuthority("SCOPE_SUPPLIER","SCOPE_ADMIN","SCOPE_EDITOR","SCOPE_STAFF")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/supplier/**").hasAnyAuthority("SCOPE_SUPPLIER","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/supplier/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/supplier/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_EDITOR","SCOPE_SUPPLIER")
//                        //Staff
//                        .requestMatchers(HttpMethod.GET,"/api/v1/staffs/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/staffs/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"api/v1/staffs/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/staffs/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        //category
//                        .requestMatchers(HttpMethod.GET,"/api/v1/categorys/**").hasAnyAuthority("SCOPE_USER")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/categorys/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/categorys/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/categorys/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        //shipper
//                        .requestMatchers(HttpMethod.GET,"/api/v1/shippers/**").hasAnyAuthority("SCOPE_SHIPPER","SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/shippers/**").hasAnyAuthority("SCOPE_SHIPPER","SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/shippers/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/shippers/**").hasAnyAuthority("SCOPE_SHIPPER","SCOPE_ADMIN","SCOPE_EDITOR")
//                        //product
//                        .requestMatchers(HttpMethod.GET,"/api/v1/products/**").hasAnyAuthority("SCOPE_USER")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/products/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/products/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/products/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        //import
//                        .requestMatchers(HttpMethod.GET,"/api/v1/imports/**").hasAnyAuthority("SCOPE_USER")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/imports/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/imports/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/imports/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        //sale
//                        .requestMatchers(HttpMethod.GET,"/api/v1/sales/**").hasAnyAuthority("SCOPE_USER")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/sales/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/sales/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/sales/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_EDITOR")
//                        //payment
//                        .requestMatchers(HttpMethod.GET,"/api/v1/payments/**").hasAnyAuthority("SCOPE_CUSTOMER","SCOPE_STAFF","SCOPE_ADMIN","EDITOR")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/payments/**").hasAnyAuthority("SCOPE_CUSTOMER")
//                        .requestMatchers(HttpMethod.PATCH,"api/v1/payments/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"api/v1/payments/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        //order
//                        .requestMatchers(HttpMethod.GET,"/api/v1/orders/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/orders/**").hasAnyAuthority("SCOPE_CUSTOMER")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/orders/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/orders/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        //invoice
//                        .requestMatchers(HttpMethod.GET,"/api/v1/invoices/**").hasAnyAuthority("SCOPE_CUSTOMER","SCOPE_STAFF","SCOPE_ADMIN","EDITOR")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/invoices/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/invoices/**").hasAnyAuthority("SCOPE_STAFF","SCOPE_ADMIN","SCOPE_EDITOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/invoices/**").hasAuthority("SCOPE_ADMIN")

                        .anyRequest().authenticated());

        /*// security mechanism (HTTP Basic Auth)
        //HTTP Basic Auth (UserName & Password)
        http.httpBasic(Customizer.withDefaults());*/

        // security mechanism (HTTP JWT)
        //http.oauth2ResourceServer(jwt->jwt.jwt(Customizer.withDefaults()));

        //not defaults JWT
        http.oauth2ResourceServer(jwt->jwt
                .jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(jwtDecoder)));

        //disable CSRF Token
        http.csrf(AbstractHttpConfigurer::disable);

        //mark Stateless session
        http.sessionManagement(session->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();//return http obj
    };
}
