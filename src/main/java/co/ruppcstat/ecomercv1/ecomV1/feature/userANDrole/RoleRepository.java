package co.ruppcstat.ecomercv1.ecomV1.feature.userANDrole;

import co.ruppcstat.ecomercv1.ecomV1.deman.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    //jpql =jakarta persistent query  Language
    //USER
    @Query("select r1 from Role as r1 where r1.roleName='USER' ")
    Role findByRoleUser();

    //CUSTOMER
    @Query("select r2 from Role as r2 where r2.roleName='CUSTOMER' ")
    Role findByRoleCustomer();

    //STAFF
    @Query("select r3 from Role as r3 where r3.roleName='STAFF' ")
    Role findByRoleStaff();


    //SUPPLIER
    @Query("select r4 from Role as r4 where r4.roleName='SUPPLIER' ")
    Role findByRoleSupplier();

    //SHIPPER
    @Query("select r5 from Role as r5 where r5.roleName='SHIPPER' ")
    Role findByRoleShipper();

    //ADMIN
    @Query("select r6 from Role as r6 where r6.roleName='ADMIN' ")
    Role findByRoleAdmin();

    //EDITOR
    @Query("select r7 from Role as r7 where r7.roleName='EDITOR' ")
    Role findByRoleEditor();


}
