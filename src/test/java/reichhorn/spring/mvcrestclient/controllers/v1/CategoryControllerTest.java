package reichhorn.spring.mvcrestclient.controllers.v1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reichhorn.spring.mvcrestclient.api.v1.model.CategoryDTO;
import reichhorn.spring.mvcrestclient.services.CategoryService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    public static final String NAME = "Fruit";

    @Mock
    CategoryService categoryService;

    // @InjectMocks automatically injects the Service into the controller,
    // so the controller does not need to be build inside the setUp method.
    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }


    @Test
    public void testListCategories() throws Exception {
        // given
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1L);
        category1.setName(NAME);

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(2L);
        category2.setName("Meat");

        List<CategoryDTO> categoryDTOS = Arrays.asList(category1, category2);

        // when
        when(categoryService.getAllCategories()).thenReturn(categoryDTOS);

        // then
        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void testGetCategoryByName() throws Exception {
        // given
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName(NAME);

        // when
        when(categoryService.getCategoryByName(anyString())).thenReturn(category);

        // then
        mockMvc.perform(get("/api/v1/categories/Fruit")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }
}