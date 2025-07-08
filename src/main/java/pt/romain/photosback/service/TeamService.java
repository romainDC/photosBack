package pt.romain.photosback.service;

import pt.romain.photosback.dto.TeamDto;
import pt.romain.photosback.entities.Team;

public interface TeamService
{
    TeamDto convertEntityToDto(Team team);

    Team convertDtoToEntity(TeamDto teamDto);

    Team save(Team team);
}
