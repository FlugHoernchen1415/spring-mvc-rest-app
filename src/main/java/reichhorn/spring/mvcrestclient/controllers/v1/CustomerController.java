package reichhorn.spring.mvcrestclient.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {

        // @RequestBody tells Spring to look at the body of the request
        // and parse it and try to create a customerDTO out of it

        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }
}
