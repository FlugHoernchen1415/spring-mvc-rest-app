package reichhorn.spring.mvcrestclient.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reichhorn.spring.mvcrestclient.model.Category;
import reichhorn.spring.mvcrestclient.model.Customer;
import reichhorn.spring.mvcrestclient.repositories.CategoryRepository;
import reichhorn.spring.mvcrestclient.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        // CommandLineRunner is specific to Spring Boot and runs on startup
        // and can also work with arguments passed in by the JVM

        initCategories();
        initCustomers();
    }

    private void initCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println(categoryRepository.count() + " Categories loaded");
    }

    private void initCustomers() {
        Customer jon = new Customer();
        jon.setId(1L);
        jon.setFirstname("Jon");
        jon.setLastname("Doe");

        Customer alice = new Customer();
        alice.setId(2L);
        alice.setFirstname("Alice");
        alice.setLastname("Wonder");

        Customer bob = new Customer();
        bob.setId(3L);
        bob.setFirstname("Bob");
        bob.setLastname("Baumeister");

        customerRepository.save(jon);
        customerRepository.save(alice);
        customerRepository.save(bob);

        System.out.println(customerRepository.count() + " Customers loaded");
    }
}
