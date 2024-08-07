package co.ruppcstat.ecomercv1.ecomV1.feature.customer;

import co.ruppcstat.ecomercv1.ecomV1.deman.Customer;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerUpdate;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerCreate customerCreate);
    CustomerResponse updateCustomer(String phone,CustomerUpdate customerUpdate);
    void deleteCustomer(Integer id);

    List<CustomerResponse> getCustomers();
    CustomerResponse getByphone(String phone);
    void updateIsDeleted(String phone);


}
