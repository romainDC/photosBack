package pt.romain.photosback.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.romain.photosback.dto.CategoryDto;
import pt.romain.photosback.entities.Category;

@SpringBootTest
class CategoryServiceTests
{
    private static final String EXPECTED_TITLE = "Test Title";
    private static final String EXPECTED_DESCRIPTION = "Test Title";

    @Autowired
    private CategoryService categoryService;

    @Test
    void testConvertEntityToDto()
    {
        final Category category = Category.builder().title(EXPECTED_TITLE).description(EXPECTED_DESCRIPTION).build();
        final CategoryDto actualCategoryDto = categoryService.convertEntityToDto(category);
        Assertions.assertNotNull(actualCategoryDto);
        Assertions.assertEquals(EXPECTED_TITLE, actualCategoryDto.title());
        Assertions.assertEquals(EXPECTED_DESCRIPTION, actualCategoryDto.description());
    }

    @Test
    void testConvertDtoToEntity()
    {
        final CategoryDto categoryDto = CategoryDto.builder().title(EXPECTED_TITLE).description(EXPECTED_DESCRIPTION).build();
        final Category actualCategory = categoryService.convertDtoToEntity(categoryDto);
        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(EXPECTED_TITLE, actualCategory.getTitle());
        Assertions.assertEquals(EXPECTED_DESCRIPTION, actualCategory.getDescription());
    }

    @Test
    void testConvertEntityToDtoNull()
    {
        Assertions.assertNull(categoryService.convertEntityToDto(null));
    }

    @Test
    void testConvertDtoToEntityNull()
    {
        Assertions.assertNull(categoryService.convertDtoToEntity(null));
    }
}
