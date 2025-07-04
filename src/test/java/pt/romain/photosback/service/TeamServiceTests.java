package pt.romain.photosback.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.romain.photosback.dto.TeamDto;
import pt.romain.photosback.entities.Team;

@SpringBootTest
class TeamServiceTests
{
    private static final String EXPECTED_TITLE = "Test Title";
    private static final String EXPECTED_DESCRIPTION = "Test Description";

    @Autowired
    private TeamService teamService;

    @Test
    void testConvertEntityToDto()
    {
        final Team team = Team.builder().title(EXPECTED_TITLE).description(EXPECTED_DESCRIPTION).build();
        final TeamDto actualTeamDto = teamService.convertEntityToDto(team);
        Assertions.assertNotNull(actualTeamDto);
        Assertions.assertEquals(EXPECTED_TITLE, actualTeamDto.title());
        Assertions.assertEquals(EXPECTED_DESCRIPTION, actualTeamDto.description());
    }

    @Test
    void testConvertDtoToEntity()
    {
        final TeamDto teamDto = TeamDto.builder().title(EXPECTED_TITLE).description(EXPECTED_DESCRIPTION).build();
        final Team actualTeam = teamService.convertDtoToEntity(teamDto);
        Assertions.assertNotNull(actualTeam);
        Assertions.assertEquals(EXPECTED_TITLE, actualTeam.getTitle());
        Assertions.assertEquals(EXPECTED_DESCRIPTION, actualTeam.getDescription());
    }

    @Test
    void testConvertEntityToDtoNull()
    {
        Assertions.assertNull(teamService.convertEntityToDto(null));
    }

    @Test
    void testConvertDtoToEntityNull()
    {
        Assertions.assertNull(teamService.convertDtoToEntity(null));
    }
}
