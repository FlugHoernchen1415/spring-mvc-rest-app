package reichhorn.spring.mvcrestclient.services;

import reichhorn.spring.mvcrestclient.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
