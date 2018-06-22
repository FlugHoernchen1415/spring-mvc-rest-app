package reichhorn.spring.mvcrestclient.api.v1.mapper;

import org.junit.Test;
import reichhorn.spring.mvcrestclient.api.v1.model.CategoryDTO;
import reichhorn.spring.mvcrestclient.model.Category;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {
        // given
        Category category = new Category();
        category.setName("fruits");
        category.setId(1L);

        // when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        // then
        assertEquals(Long.valueOf(1L), categoryDTO.getId());
        assertEquals("fruits", categoryDTO.getName());
    }
}