package reichhorn.spring.mvcrestclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reichhorn.spring.mvcrestclient.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
