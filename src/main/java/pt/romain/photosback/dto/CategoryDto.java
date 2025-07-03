package pt.romain.photosback.dto;

import lombok.Builder;

@Builder
public record CategoryDto(
        Long id,
        String title,
        String description)
{
}
