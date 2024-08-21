package co.ruppcstat.ecomercv1.ecomV1.security;

import co.ruppcstat.ecomercv1.ecomV1.deman.User;
import co.ruppcstat.ecomercv1.ecomV1.feature.userANDrole.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneAndIsDeletedFalse(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User phoneNumber not found"));
        log.info("user: {}",user.getPhone());
        CustomerUserDetails customerUserDetails=new CustomerUserDetails();
        customerUserDetails.setUser(user);
        return customerUserDetails;
    }
}
