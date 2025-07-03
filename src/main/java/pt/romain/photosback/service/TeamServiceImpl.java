package pt.romain.photosback.service;

import org.springframework.stereotype.Service;
import pt.romain.photosback.dto.TeamDto;
import pt.romain.photosback.entities.Team;

@Service
public class TeamServiceImpl implements TeamService
{
    @Override
    public TeamDto convertEntityToDto(Team team)
    {
        if (team == null)
            return null;
        return TeamDto.builder()
                .id(team.getId())
                .title(team.getTitle())
                .description(team.getDescription())
                .build();
    }

    @Override
    public Team convertDtoToEntity(TeamDto teamDto)
    {
        if (teamDto == null)
            return null;
        return Team.builder()
                .id(teamDto.id())
                .title(teamDto.title())
                .description(teamDto.description())
                .build();
    }
}
