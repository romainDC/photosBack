package pt.romain.photosback.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.romain.photosback.dto.UserDto;
import pt.romain.photosback.entities.User;

@SpringBootTest
class UserServiceTests
{
    private static final String EXPECTED_LOGIN = "testLogin";
    private static final String EXPECTED_PASSWORD = "testPassword";
    private static final String EXPECTED_MAIL = "testMail";

    @Autowired
    private UserService userService;

    @Test
    void testConvertEntityToDto()
    {
        final User user = User.builder().login(EXPECTED_LOGIN).password(EXPECTED_PASSWORD).mail(EXPECTED_MAIL).build();
        final UserDto actualUserDto = userService.convertEntityToDto(user);
        Assertions.assertNotNull(actualUserDto);
        Assertions.assertEquals(EXPECTED_LOGIN, actualUserDto.login());
        Assertions.assertEquals(EXPECTED_PASSWORD, actualUserDto.password());
        Assertions.assertEquals(EXPECTED_MAIL, actualUserDto.mail());
    }

    @Test
    void testConvertDtoToEntity()
    {
        final UserDto userDto = UserDto.builder().login(EXPECTED_LOGIN).password(EXPECTED_PASSWORD).mail(EXPECTED_MAIL).build();
        final User actualUser = userService.convertDtoToEntity(userDto);
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(EXPECTED_LOGIN, actualUser.getLogin());
        Assertions.assertEquals(EXPECTED_PASSWORD, actualUser.getPassword());
        Assertions.assertEquals(EXPECTED_MAIL, actualUser.getMail());
    }

    @Test
    void testConvertEntityToDtoNull()
    {
        Assertions.assertNull(userService.convertEntityToDto(null));
    }

    @Test
    void testConvertDtoToEntityNull()
    {
        Assertions.assertNull(userService.convertDtoToEntity(null));
    }
}
