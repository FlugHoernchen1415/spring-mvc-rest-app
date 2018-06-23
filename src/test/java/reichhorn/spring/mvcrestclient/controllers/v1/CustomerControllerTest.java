package reichhorn.spring.mvcrestclient.controllers.v1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reichhorn.spring.mvcrestclient.api.v1.model.CustomerDTO;
import reichhorn.spring.mvcrestclient.services.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }


    @Test
    public void getAllCustomers() throws Exception {
        // given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname("Jon");
        customer1.setLastname("Doe");
        customer1.setCustomerUrl("/api/v1/customer/1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname("Jon");
        customer2.setLastname("Doe");
        customer2.setCustomerUrl("/api/v1/customer/2");

        List<CustomerDTO> customerDTOS = Arrays.asList(customer1, customer2);

        // when
        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        // then
        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerByFirstname() throws Exception {
        // given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Jon");
        customer.setLastname("Doe");
        customer.setCustomerUrl("/api/v1/customer/1");

        // when
        when(customerService.getCustomerByFirstname(anyString())).thenReturn(customer);

        // then
        mockMvc.perform(get("/api/v1/customers/Jon")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Jon")));
    }
}