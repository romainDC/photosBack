package pt.romain.photosback.service;

import pt.romain.photosback.dto.UserDto;
import pt.romain.photosback.entities.User;

public interface UserService
{
    UserDto convertEntityToDto(User user);

    User convertDtoToEntity(UserDto userDto);
}
