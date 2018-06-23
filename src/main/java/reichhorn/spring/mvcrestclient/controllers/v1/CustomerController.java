package reichhorn.spring.mvcrestclient.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reichhorn.spring.mvcrestclient.api.v1.model.CustomerDTO;
import reichhorn.spring.mvcrestclient.api.v1.model.CustomerListDTO;
import reichhorn.spring.mvcrestclient.services.CustomerService;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {

        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }


    @GetMapping("{firstname}")
    public ResponseEntity<CustomerDTO> getCustomerByFirstname(@PathVariable String firstname) {

        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerByFirstname(firstname), HttpStatus.OK);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
//
//        return new ResponseEntity<CustomerDTO>(
//                customerService.getCustomerById(id), HttpStatus.OK);
//    }
}
