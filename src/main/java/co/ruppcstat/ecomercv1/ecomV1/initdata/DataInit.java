package co.ruppcstat.ecomercv1.ecomV1.initdata;

import co.ruppcstat.ecomercv1.ecomV1.deman.*;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.CategoryRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.CustomerRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.ProductRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.ShipperRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.SupplierRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.userANDrole.RoleRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.userANDrole.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository CustomerRepository;
    private final SupplierRepository supplierRepository;
    private final StaffRepository staffRepository;
    private final CategoryRepository categoryRepository;
    private final ShipperRepository shipperRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    void init() {
        if(userRepository.count() == 0) {
            Role user=new Role();
            user.setRoleName("USER");

            Role customer=new Role();
            customer.setRoleName("CUSTOMER");

            Role staff=new Role();
            staff.setRoleName("STAFF");

            Role supplier=new Role();
            supplier.setRoleName("SUPPLIER");

            Role shipper=new Role();
            shipper.setRoleName("SHIPPER");

            Role admin=new Role();
            admin.setRoleName("ADMIN");

            Role editor=new Role();
            editor.setRoleName("EDITOR");

            roleRepository.saveAll(List.of(user,customer,staff,supplier,shipper,admin,editor));

            User user1=new User();
            user1.setUserName("sunlyCustomer");
            user1.setPassword(passwordEncoder.encode("Sunly123"));
            user1.setEmail("sunlyUser1@gmail.com");
            user1.setGender("male");
            user1.setPhone("0967621922");
            user1.setIsBlock(false);
            user1.setIsDeleted(false);
            user1.setIsVerified(true);
            user1.setRoles(List.of(user,customer));

            User user2=new User();
            user2.setUserName("sunlyStaff");
            user2.setPassword(passwordEncoder.encode("Sunly123"));
            user2.setEmail("sunlyUser2@gmail.com");
            user2.setGender("female");
            user2.setPhone("09676219222");
            user2.setIsBlock(false);
            user2.setIsDeleted(false);
            user2.setIsVerified(true);
            user2.setRoles(List.of(user,staff));

            User user3=new User();
            user3.setUserName("sunlySupplier");
            user3.setPassword(passwordEncoder.encode("Sunly123"));
            user3.setEmail("sunlyUser3@gmail.com");
            user3.setGender("male");
            user3.setPhone("09676219223");
            user3.setIsBlock(false);
            user3.setIsDeleted(false);
            user3.setIsVerified(true);
            user3.setRoles(List.of(user,supplier));

            User user4=new User();
            user4.setUserName("sunlyAdmin");
            user4.setPassword(passwordEncoder.encode("Sunly123"));
            user4.setEmail("sunlyUser4@gmail.com");
            user4.setGender("female");
            user4.setPhone("09676219224");
            user4.setIsBlock(false);
            user4.setIsDeleted(false);
            user4.setIsVerified(true);
            user4.setRoles(List.of(user,admin));

            User user5=new User();
            user5.setUserName("sunlyEditor");
            user5.setPassword(passwordEncoder.encode("Sunly123"));
            user5.setEmail("sunlyEditor5@gmail.com");
            user5.setGender("male");
            user5.setPhone("09676219225");
            user5.setIsBlock(false);
            user5.setIsDeleted(false);
            user5.setIsVerified(true);
            user5.setRoles(List.of(user,editor));

            User user6=new User();
            user6.setUserName("sunlyShiper");
            //user6.setPassword("Sunly123");
            user6.setPassword(passwordEncoder.encode("Sunly123"));
            user6.setEmail("sunlyShiper5@gmail.com");
            user6.setGender("female");
            user6.setPhone("09676219226");
            user6.setIsBlock(false);
            user6.setIsDeleted(false);
            user6.setIsVerified(true);
            user6.setRoles(List.of(user,shipper));

            userRepository.saveAll(List.of(user1,user2,user3,user4,user5,user6));

        }
        if (CustomerRepository.count() == 0) {
            Customer customer = new Customer();
            customer.setName("Customer 1");
            customer.setAddress("kan dal");
            customer.setPhone("096766666");
            customer.setEmail("sunlyslysun@gamil.com");
            customer.setType("VIP");
            customer.setIsDeleted(false);
            CustomerRepository.save(customer);
        }
        if (supplierRepository.count() == 0) {
            Supplier supplier = new Supplier();
            supplier.setName("Supplier 1");
            supplier.setContactPhone("0987654321");
            supplier.setIsDeleted(false);
            supplier.setContactAddress("ko kung");
            supplierRepository.save(supplier);
        }
        if (productRepository.count() == 0){
            Product product = new Product();
            product.setPrice(12f);
            product.setKeyId("10");
            product.setName("Product 1");
            product.setImage("image 1");

            Product product2 = new Product();
            product2.setPrice(22f);
            product2.setKeyId("20");
            product2.setName("Product 2");
            product2.setImage("image 2");

            Product product3 = new Product();
            product3.setPrice(33f);
            product3.setKeyId("30");
            product3.setName("Product 3");
            product3.setImage("image 3");

            productRepository.saveAll(List.of(product, product2, product3));
        }
        if (staffRepository.count() == 0) {
            Staff staff = new Staff();
            staff.setHiredDate(LocalDate.now());
            staff.setStopWork(false);
            staff.setImage("sunly.jpg");
            staff.setGender("Male");
            staff.setPhone("0987654321");
            staff.setNameKH("សុខ លា");
            staff.setNameEN("sok la");
            staff.setSalary(1200f);
            staff.setPosition("sale at office");
            staffRepository.save(staff);
        }
        if (categoryRepository.count() == 0) {
            Category categoryAcer = new Category();
            categoryAcer.setName("laptop-gaming-acer");
            categoryAcer.setDescription("my computer is so strong for coding and play game ");
            categoryAcer.setIsDeleted(false);
            categoryRepository.save(categoryAcer);

            Category categoryMSI = new Category();
            categoryMSI.setName("mimi-gaming-GRI");
            categoryMSI.setDescription("my computer is so strong for coding");
            categoryMSI.setIsDeleted(false);
            categoryRepository.save(categoryMSI);

            Category categoryLaptop = new Category();
            categoryLaptop.setName("laptop");
            categoryLaptop.setDescription("my computer is so strong for coding");
            categoryLaptop.setIsDeleted(false);
            categoryRepository.save(categoryLaptop);
        }
        if (shipperRepository.count() == 0) {
            Shipper shipper = new Shipper();
            shipper.setContactPhone("098765456");
            shipper.setContactAddress("pray sor");
            shipper.setIsDeleted(false);
            shipper.setName("konako");
            shipperRepository.save(shipper);
        }


    }
}
