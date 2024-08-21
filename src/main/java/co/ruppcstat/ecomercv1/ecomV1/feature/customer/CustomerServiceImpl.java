package co.ruppcstat.ecomercv1.ecomV1.feature.customer;

import co.ruppcstat.ecomercv1.ecomV1.deman.Customer;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerUpdate;
import co.ruppcstat.ecomercv1.ecomV1.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public CustomerResponse createCustomer(CustomerCreate customerCreate) {
        if(customerRepository.existsByPhone(customerCreate.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }
        if(customerRepository.existsByEmail(customerCreate.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        Customer customer=
                customerMapper.dtoToEntity(customerCreate);
        customer.setIsDeleted(false);
        customer=customerRepository.save(customer);
        return customerMapper.entityToDto(customer);
    }

    @Override
    public CustomerResponse updateCustomer(String phone, CustomerUpdate customerUpdate) {
        Customer customer=customerRepository.findByPhone(phone)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone number not found"));
        customerMapper.updateEntityToDto(customer, customerUpdate);
        customer=customerRepository.save(customer);
        return customerMapper.entityToDto(customer);
    }

    @Override
    public void deleteCustomer(String phone) {
        Customer customer=customerRepository.findByPhone(phone)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"id not found"));
        customerRepository.delete(customer);
    }


    @Override
    public Page<CustomerResponse> getCustomers(int pageNumber, int pageSize) {
       // Sort sortById=Sort.by(Sort.Direction.DESC,"id");
        Sort sortById = Sort.by(Sort.Direction.DESC, "customerID");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Customer> customerList=customerRepository.findAll(pageRequest);
        return customerList.map(customerMapper::entityToDto);
    }

    @Override
    public CustomerResponse getByphone(String phone) {
        Customer customer=customerRepository.findByPhone(phone)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        return customerMapper.entityToDto(customer);
    }

    @Override
    public void updateIsDeleted(String phone ) {
        Customer customer=customerRepository.findByPhone(phone)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        customer.setIsDeleted(true);
        customerRepository.save(customer);
    }


}
