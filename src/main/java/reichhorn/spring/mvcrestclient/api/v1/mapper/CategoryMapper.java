package reichhorn.spring.mvcrestclient.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import reichhorn.spring.mvcrestclient.api.v1.model.CategoryDTO;
import reichhorn.spring.mvcrestclient.model.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
