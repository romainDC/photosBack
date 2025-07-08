package pt.romain.photosback.service;

import org.springframework.stereotype.Service;
import pt.romain.photosback.dto.TeamDto;
import pt.romain.photosback.entities.Team;
import pt.romain.photosback.repository.TeamRepository;

import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService
{

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;
    }

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

    @Override
    public Team save(Team team)
    {
        if (team == null)
            return null;
        Optional<Team> optionalTeam = teamRepository.findAll().stream().filter(team::equals).findFirst();
        return optionalTeam.orElseGet(() -> teamRepository.save(team));
    }
}
