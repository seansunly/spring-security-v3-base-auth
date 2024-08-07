package co.ruppcstat.ecomercv1.ecomV1.mapper;

import co.ruppcstat.ecomercv1.ecomV1.deman.Customer;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.customer.DTOCustomer.CustomerUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer dtoToEntity(CustomerCreate customerCreate);
    CustomerResponse entityToDto(Customer customer);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityToDto(@MappingTarget Customer customer, CustomerUpdate customerUpdate);
    List<CustomerResponse> entityToDto(List<Customer> customers);
}
