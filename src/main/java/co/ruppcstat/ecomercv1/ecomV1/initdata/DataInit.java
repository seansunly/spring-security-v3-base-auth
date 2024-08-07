package co.ruppcstat.ecomercv1.ecomV1.initdata;

import co.ruppcstat.ecomercv1.ecomV1.deman.*;
import co.ruppcstat.ecomercv1.ecomV1.feature.category.CategoryRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.CustomerRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.ProductRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.shipper.ShipperRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.supplier.SupplierRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final CustomerRepository CustomerRepository;
    private final SupplierRepository supplierRepository;
    private final StaffRepository staffRepository;
    private final CategoryRepository categoryRepository;
    private final ShipperRepository shipperRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    void init() {
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
