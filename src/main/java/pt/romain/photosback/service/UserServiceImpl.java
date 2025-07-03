package pt.romain.photosback.service;

import org.springframework.stereotype.Service;
import pt.romain.photosback.dto.UserDto;
import pt.romain.photosback.entities.User;

@Service
public class UserServiceImpl implements UserService
{
    private final TeamService teamService;

    public UserServiceImpl(TeamService teamService)
    {
        this.teamService = teamService;
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
}
