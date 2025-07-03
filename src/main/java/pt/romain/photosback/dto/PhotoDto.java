package pt.romain.photosback.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record PhotoDto(
        Long id,
        String title,
        String description,
        String imageUrl,
        String thumbnailUrl,
        String filename,
        String contentType,
        Long fileSize,
        LocalDateTime uploadedAt,
        CategoryDto category,
        UserDto owner,
        Set<TeamDto> teams
)
{
}
