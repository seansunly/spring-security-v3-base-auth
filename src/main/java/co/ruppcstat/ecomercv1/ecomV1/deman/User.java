package co.ruppcstat.ecomercv1.ecomV1.deman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    @Column( nullable = false,length = 20)
    private String userName;
    @Column( nullable = false)
    private String password;
    private String confirmPassword;
    @Column( nullable = false,unique = true)
    private String email;
    @Column( nullable = false,unique = true)
    private String phone;
    private String gender;
    private Boolean acceptTerm;
    @Column( nullable = false)
    private Boolean isDeleted;
    @Column( nullable = false)
    private Boolean isBlock;
    private Boolean isVerified;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleID"))
    private List<Role> roles;
}
