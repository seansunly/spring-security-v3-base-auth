package co.ruppcstat.ecomercv1.ecomV1.feature.customer;

import co.ruppcstat.ecomercv1.ecomV1.deman.Customer;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerUpdate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerCreate customerCreate);
    CustomerResponse updateCustomer(String phone,CustomerUpdate customerUpdate);
    void deleteCustomer(String phone);

    Page<CustomerResponse> getCustomers(int pageNumber, int pageSize);
    CustomerResponse getByphone(String phone);
    void updateIsDeleted(String phone);


}
