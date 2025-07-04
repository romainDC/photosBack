package pt.romain.photosback.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.romain.photosback.dto.UserDto;
import pt.romain.photosback.entities.User;
import pt.romain.photosback.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
    private final TeamService teamService;
    private final UserRepository userRepository;

    public UserServiceImpl(TeamService teamService, UserRepository userRepository)
    {
        this.teamService = teamService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto convertEntityToDto(User user)
    {
        if (user == null)
            return null;
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .mail(user.getMail())
                .createdAt(user.getCreatedAt())
                .team(teamService.convertEntityToDto(user.getTeam()))
                .build();
    }

    @Override
    public User convertDtoToEntity(UserDto userDto)
    {
        if (userDto == null)
            return null;
        return User.builder()
                .id(userDto.id())
                .login(userDto.login())
                .password(userDto.password())
                .mail(userDto.mail())
                .createdAt(userDto.createdAt())
                .team(teamService.convertDtoToEntity(userDto.team()))
                .build();
    }

    @Transactional
    @Override
    public UserDto registerNewUser(UserDto userDto)
    {
        if (userDto == null)
            return null;
        User user = convertDtoToEntity(userDto);
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Username already taken.");
        }
        if (userRepository.findByMail(user.getMail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered.");
        }
        return convertEntityToDto(userRepository.save(user));
    }
}
