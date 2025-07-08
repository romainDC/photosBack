package pt.romain.photosback.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pt.romain.photosback.dto.UserDto;
import pt.romain.photosback.entities.User;
import pt.romain.photosback.repository.UserRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTests
{
    private static final String LOGIN = "testLogin";
    private static final String PASSWORD = "testPassword";
    private static final String MAIL = "testMail";
    private static final String ENCODED_PASSWORD_MOCK = "encodedTestPassword"; // Un mot de passe encodé simulé

    private static UserRepository userRepositoryMock;
    private static UserService userServiceMock;
    private static PasswordEncoder passwordEncoderMock;

    @BeforeAll
    static void setUpTests()
    {
        userRepositoryMock = mock(UserRepository.class);
        passwordEncoderMock = mock(PasswordEncoder.class);
        TeamService teamServiceMock = mock(TeamService.class);
        userServiceMock = new UserServiceImpl(passwordEncoderMock, teamServiceMock, userRepositoryMock);
    }

    @Test
    void testConvertEntityToDto()
    {
        final User user = User.builder().login(LOGIN).password(PASSWORD).mail(MAIL).build();
        final UserDto actualUserDto = userServiceMock.convertEntityToDto(user);
        Assertions.assertNotNull(actualUserDto);
        Assertions.assertEquals(LOGIN, actualUserDto.login());
        Assertions.assertEquals(PASSWORD, actualUserDto.password());
        Assertions.assertEquals(MAIL, actualUserDto.mail());
    }

    @Test
    void testConvertDtoToEntity()
    {
        final UserDto userDto = UserDto.builder().login(LOGIN).password(PASSWORD).mail(MAIL).build();
        final User actualUser = userServiceMock.convertDtoToEntity(userDto);
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(LOGIN, actualUser.getLogin());
        Assertions.assertEquals(PASSWORD, actualUser.getPassword());
        Assertions.assertEquals(MAIL, actualUser.getMail());
    }

    @Test
    void testConvertEntityToDtoNull()
    {
        Assertions.assertNull(userServiceMock.convertEntityToDto(null));
    }

    @Test
    void testConvertDtoToEntityNull()
    {
        Assertions.assertNull(userServiceMock.convertDtoToEntity(null));
    }

    @Test
    void testRegisterNewUserNull()
    {
        Assertions.assertNull(userServiceMock.registerNewUser(null));
    }

    @Test
    void testRegisterNewUserLogin()
    {
        final UserDto userDto = UserDto.builder().login(LOGIN).password(PASSWORD).mail(MAIL).build();
        when(userRepositoryMock.findByLogin(LOGIN)).thenReturn(Optional.of(new User()));

        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> userServiceMock.registerNewUser(userDto), "Username already taken.");
    }

    @Test
    void testRegisterNewUserMail()
    {
        final UserDto userDto = UserDto.builder().login(LOGIN).password(PASSWORD).mail(MAIL).build();
        when(userRepositoryMock.findByLogin(LOGIN)).thenReturn(Optional.empty());
        when(userRepositoryMock.findByMail(MAIL)).thenReturn(Optional.of(new User()));

        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> userServiceMock.registerNewUser(userDto), "Email already registered.");
    }

    @Test
    void testRegisterNewUser()
    {
        final UserDto userDto = UserDto.builder().login(LOGIN).password(PASSWORD).mail(MAIL).build();
        when(userRepositoryMock.findByLogin(LOGIN)).thenReturn(Optional.empty());
        when(userRepositoryMock.findByMail(MAIL)).thenReturn(Optional.empty());
        when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0)); // Returns the first argument passed to save()
        when(passwordEncoderMock.encode(Mockito.anyString())).thenReturn(ENCODED_PASSWORD_MOCK);

        UserDto actualUserDto = userServiceMock.registerNewUser(userDto);

        verify(userRepositoryMock, times(1)).save(Mockito.any(User.class));
        Assertions.assertNotNull(actualUserDto);
        Assertions.assertEquals(LOGIN, actualUserDto.login());
        Assertions.assertEquals(ENCODED_PASSWORD_MOCK, actualUserDto.password());
        Assertions.assertEquals(MAIL, actualUserDto.mail());

        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        verify(passwordEncoderMock, times(1)).encode(passwordCaptor.capture());
        Assertions.assertEquals(PASSWORD, passwordCaptor.getValue(), "The original password should have been passed to the encoder.");
    }
}
