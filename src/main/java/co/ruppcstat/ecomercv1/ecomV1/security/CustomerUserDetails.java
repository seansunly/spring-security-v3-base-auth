package co.ruppcstat.ecomercv1.ecomV1.security;

import co.ruppcstat.ecomercv1.ecomV1.deman.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class CustomerUserDetails implements UserDetails {
    //user in domain modal table
    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();//password user obj
    }

    @Override
    public String getUsername() {
        return user.getPhone();//get unit file
    }
}
