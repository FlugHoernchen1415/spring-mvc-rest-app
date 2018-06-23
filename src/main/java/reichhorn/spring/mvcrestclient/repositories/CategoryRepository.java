package reichhorn.spring.mvcrestclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reichhorn.spring.mvcrestclient.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);
}
