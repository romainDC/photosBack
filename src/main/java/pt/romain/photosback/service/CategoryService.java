package pt.romain.photosback.service;

import pt.romain.photosback.dto.CategoryDto;
import pt.romain.photosback.entities.Category;

public interface CategoryService
{
    CategoryDto convertEntityToDto(Category category);

    Category convertDtoToEntity(CategoryDto categoryDto);    
}
