package reichhorn.spring.mvcrestclient.api.v1.mapper;

import org.junit.Test;
import reichhorn.spring.mvcrestclient.api.v1.model.CustomerDTO;
import reichhorn.spring.mvcrestclient.model.Customer;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;


    @Test
    public void customerToCustomerDTO() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstname("Jon");
        customer.setLastname("Doe");

        // when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        // then
        assertEquals("Jon", customerDTO.getFirstname());
        assertEquals("Doe", customerDTO.getLastname());
    }
}