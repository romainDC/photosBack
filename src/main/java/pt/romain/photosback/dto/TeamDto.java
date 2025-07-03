package pt.romain.photosback.dto;

import lombok.Builder;

@Builder
public record TeamDto(
        Long id,
        String title,
        String description
)
{
}
