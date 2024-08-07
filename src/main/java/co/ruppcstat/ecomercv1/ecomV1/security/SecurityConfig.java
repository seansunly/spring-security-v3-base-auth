package co.ruppcstat.ecomercv1.ecomV1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    //use for any user a lot of user
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

    }
    @Bean
    SecurityFilterChain configureAPISecurity(HttpSecurity http) throws Exception {
        //EndPoint security config
        http.authorizeHttpRequests(endpoint->
                endpoint
                        .requestMatchers(HttpMethod.POST,"/api/v1/customers/**").hasAnyRole("EDITOR","ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/customers/**").hasAnyRole("ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/customers/**").hasAnyRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET,"/api/v1/customers/**").hasAnyRole("CUSTOMER","ADMIN","EDITOR")
                        //Supplier
                        .requestMatchers(HttpMethod.GET,"/api/v1/suppliers/**").hasAnyRole("SUPPLIER","ADMIN","EDITOR","STAFF")
                        .requestMatchers(HttpMethod.POST,"/api/v1/supplier/**").hasAnyRole("SUPPLIER","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/supplier/**").hasAnyRole("ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/supplier/**").hasAnyRole("ADMIN","EDITOR","SUPPLIER")
                        //Staff
                        .requestMatchers(HttpMethod.GET,"/api/v1/staffs/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.POST,"/api/v1/staffs/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"api/v1/staffs/**").hasAnyRole("ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/staffs/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        //category
                        .requestMatchers(HttpMethod.GET,"/api/v1/categorys/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/v1/categorys/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/categorys/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/categorys/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        //shipper
                        .requestMatchers(HttpMethod.GET,"/api/v1/shippers/**").hasAnyRole("SHIPPER","STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.POST,"/api/v1/shippers/**").hasAnyRole("SHIPPER","STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/shippers/**").hasAnyRole("ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/shippers/**").hasAnyRole("SHIPPER","ADMIN","EDITOR")
                        //product
                        .requestMatchers(HttpMethod.GET,"/api/v1/products/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/v1/products/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/products/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/products/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        //import
                        .requestMatchers(HttpMethod.GET,"/api/v1/imports/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/v1/imports/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/imports/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/imports/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        //sale
                        .requestMatchers(HttpMethod.GET,"/api/v1/sales/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/v1/sales/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/sales/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/sales/**").hasAnyRole("ADMIN","EDITOR")
                        //payment
                        .requestMatchers(HttpMethod.GET,"/api/v1/payments/**").hasAnyRole("CUSTOMER","STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.POST,"/api/v1/payments/**").hasAnyRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PATCH,"api/v1/payments/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"api/v1/payments/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        //order
                        .requestMatchers(HttpMethod.GET,"/api/v1/orders/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.POST,"/api/v1/orders/**").hasAnyRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/orders/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/orders/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        //invoice
                        .requestMatchers(HttpMethod.GET,"/api/v1/invoices/**").hasAnyRole("CUSTOMER","STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.POST,"/api/v1/invoices/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/invoices/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/invoices/**").hasAnyRole("STAFF","ADMIN","EDITOR")
                        .anyRequest().authenticated());

        // security mechanism (HTTP Basic Auth)
        //HTTP Basic Auth (UserName & Password)
        http.httpBasic(Customizer.withDefaults());

        //disable CSRF Token
        http.csrf(token->token.disable());

        //mark Stateless session
        http.sessionManagement(session->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();//return http obj
    };
}
