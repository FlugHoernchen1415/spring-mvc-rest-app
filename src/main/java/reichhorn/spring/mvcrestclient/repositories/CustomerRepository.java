package reichhorn.spring.mvcrestclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reichhorn.spring.mvcrestclient.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByFirstname(String firstname);
}
