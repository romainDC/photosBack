package pt.romain.photosback.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto(
        Long id,
        String login,
        String password,
        String mail,
        LocalDateTime createdAt,
        TeamDto team
)
{
}
