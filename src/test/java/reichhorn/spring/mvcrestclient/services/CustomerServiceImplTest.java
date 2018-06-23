package reichhorn.spring.mvcrestclient.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reichhorn.spring.mvcrestclient.api.v1.mapper.CustomerMapper;
import reichhorn.spring.mvcrestclient.api.v1.model.CustomerDTO;
import reichhorn.spring.mvcrestclient.model.Customer;
import reichhorn.spring.mvcrestclient.repositories.CustomerRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() {
        // given
        List<Customer> customerList = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        // when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        // then
        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void getCustomerByFirstname() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstname("Jon");
        customer.setLastname("Doe");

        when(customerRepository.findCustomerByFirstname(anyString())).thenReturn(customer);

        // when
        CustomerDTO customerDTO = customerService.getCustomerByFirstname("Jon");

        // then
        assertEquals("Jon", customerDTO.getFirstname());
        assertEquals("Doe", customerDTO.getLastname());
    }
}