package reichhorn.spring.mvcrestclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reichhorn.spring.mvcrestclient.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByFirstname(String firstname);
}
