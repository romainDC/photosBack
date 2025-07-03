package pt.romain.photosback.service;

import org.springframework.stereotype.Service;
import pt.romain.photosback.dto.CategoryDto;
import pt.romain.photosback.entities.Category;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Override
    public CategoryDto convertEntityToDto(Category category)
    {
        if (category == null)
            return null;
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    @Override
    public Category convertDtoToEntity(CategoryDto categoryDto)
    {
        if (categoryDto == null)
            return null;
        return Category.builder()
                .id(categoryDto.id())
                .title(categoryDto.title())
                .description(categoryDto.description())
                .build();
    }
}
