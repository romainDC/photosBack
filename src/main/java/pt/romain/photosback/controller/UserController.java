package pt.romain.photosback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.romain.photosback.dto.UserDto;
import pt.romain.photosback.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Endpoint pour l'enregistrement d'un nouvel utilisateur.
     *
     * @param userDto L'objet User (avec username, password, email) envoyé dans le corps de la requête.
     * @return ResponseEntity avec un message de succès ou d'erreur.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto)
    {
        try {
            userService.registerNewUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
