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
import reichhorn.spring.mvcrestclient.controllers.RestResponseEntityExceptionHandler;
import reichhorn.spring.mvcrestclient.services.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest{

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }


    @Test
    public void getAllCustomers() throws Exception {
        // given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname("Jon");
        customer1.setLastname("Doe");
        customer1.setCustomerUrl(CustomerController.BASE_URL + "1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname("Jon");
        customer2.setLastname("Doe");
        customer2.setCustomerUrl(CustomerController.BASE_URL + "2");

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
        customer.setCustomerUrl(CustomerController.BASE_URL + "1");

        // when
        when(customerService.getCustomerByFirstname(anyString())).thenReturn(customer);

        // then
        mockMvc.perform(get("/api/v1/customers/name/Jon")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Jon")));
    }

    @Test
    public void getCustomerById() throws Exception {
        // given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Jon");
        customer.setLastname("Doe");
        customer.setCustomerUrl(CustomerController.BASE_URL + "1");

        // when
        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        // then
        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Jon")));
    }

    @Test
    public void createNewCustomer() throws Exception {
        // given

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jon");
        customerDTO.setLastname("Doe");

        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customerDTO.getFirstname());
        returnedDTO.setLastname(customerDTO.getLastname());
        returnedDTO.setCustomerUrl(CustomerController.BASE_URL + "1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnedDTO);

        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Jon")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "1")));
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jon");
        customerDTO.setLastname("Doe");

        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customerDTO.getFirstname());
        returnedDTO.setLastname(customerDTO.getLastname());
        returnedDTO.setCustomerUrl(CustomerController.BASE_URL + "1");

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnedDTO);

        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Jon")))
                .andExpect(jsonPath("$.lastname", equalTo("Doe")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "1")));
    }

    @Test
    public void patchCustomer() throws Exception {
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jon");

        CustomerDTO returnedCustomerDTO = new CustomerDTO();
        returnedCustomerDTO.setFirstname(customerDTO.getFirstname());
        returnedCustomerDTO.setLastname("Doe");
        returnedCustomerDTO.setCustomerUrl(CustomerController.BASE_URL + "1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnedCustomerDTO);

        mockMvc.perform(patch("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Jon")))
                .andExpect(jsonPath("$.lastname", equalTo("Doe")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "1")));
    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());
    }
}