package pt.romain.photosback.service;

import pt.romain.photosback.dto.UserDto;
import pt.romain.photosback.entities.User;

public interface UserService
{
    UserDto convertEntityToDto(User user);

    User convertDtoToEntity(UserDto userDto);

    /**
     * Enregistre un nouvel utilisateur après avoir haché son mot de passe.
     *
     * @param userDto L'objet UserDto à enregistrer.
     * @return L'utilisateur enregistré.
     * @throws IllegalArgumentException si le nom d'utilisateur ou l'email existe déjà.
     */
    UserDto registerNewUser(UserDto userDto);
}
