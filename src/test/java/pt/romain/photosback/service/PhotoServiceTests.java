package pt.romain.photosback.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.romain.photosback.dto.PhotoDto;
import pt.romain.photosback.dto.TeamDto;
import pt.romain.photosback.dto.UserDto;
import pt.romain.photosback.entities.Photo;
import pt.romain.photosback.entities.Team;
import pt.romain.photosback.entities.User;

import java.util.Set;

@SpringBootTest
class PhotoServiceTests
{
    private static final String TITLE = "Test Title";
    private static final String DESCRIPTION = "Test Description";
    private static final String IMAGE_URL = """
            https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/For%C3%AAt_domaniale_d%27Armainvilliers_-_Parcelle_10_-_panoramio.jpg/960px-For%C3%AAt_domaniale_d%27Armainvilliers_-_Parcelle_10_-_panoramio.jpg""";
    private static final String THUMBNAIL_URL = """
            https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/For%C3%AAt_domaniale_d%27Armainvilliers_-_Parcelle_10_-_panoramio.jpg/120px-For%C3%AAt_domaniale_d%27Armainvilliers_-_Parcelle_10_-_panoramio.jpg""";
    private static final String FILENAME = "For%C3%AAt_domaniale_d%27Armainvilliers";
    private static final String CONTENT_TYPE = "image/jpeg";
    private static final Long FILE_SIZE = 1024L;
    private static final String LOGIN = "testLogin";
    private static final String PASSWORD = "testPassword";
    private static final String MAIL = "testMail";
    private static final String TEAM_TITLE = "Team Title";
    private static final String TEAM_DESCRIPTION = "Team Description";

    @Autowired
    PhotoService photoService;

    @Test
    void testConvertEntityToDto()
    {
        final User owner = User.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .mail(MAIL)
                .build();
        final Photo photo = Photo.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .imageUrl(IMAGE_URL)
                .thumbnailUrl(THUMBNAIL_URL)
                .filename(FILENAME)
                .contentType(CONTENT_TYPE)
                .fileSize(FILE_SIZE)
                .owner(owner)
                .build();
        final PhotoDto actualPhotoDto = photoService.convertEntityToDto(photo);
        Assertions.assertNotNull(actualPhotoDto);
        Assertions.assertEquals(TITLE, actualPhotoDto.title());
        Assertions.assertEquals(DESCRIPTION, actualPhotoDto.description());
        Assertions.assertEquals(IMAGE_URL, actualPhotoDto.imageUrl());
        Assertions.assertEquals(THUMBNAIL_URL, actualPhotoDto.thumbnailUrl());
        Assertions.assertEquals(FILENAME, actualPhotoDto.filename());
        Assertions.assertEquals(CONTENT_TYPE, actualPhotoDto.contentType());
        Assertions.assertEquals(FILE_SIZE, actualPhotoDto.fileSize());
        Assertions.assertNotNull(actualPhotoDto.owner());
        Assertions.assertEquals(LOGIN, actualPhotoDto.owner().login());
        Assertions.assertEquals(PASSWORD, actualPhotoDto.owner().password());
        Assertions.assertEquals(MAIL, actualPhotoDto.owner().mail());
    }

    @Test
    void testConvertDtoToEntity()
    {
        final UserDto ownerDto = UserDto.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .mail(MAIL)
                .build();
        final PhotoDto photoDto = PhotoDto.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .imageUrl(IMAGE_URL)
                .thumbnailUrl(THUMBNAIL_URL)
                .filename(FILENAME)
                .contentType(CONTENT_TYPE)
                .fileSize(FILE_SIZE)
                .owner(ownerDto)
                .build();
        final Photo actualPhoto = photoService.convertDtoToEntity(photoDto);
        Assertions.assertNotNull(actualPhoto);
        Assertions.assertEquals(TITLE, actualPhoto.getTitle());
        Assertions.assertEquals(DESCRIPTION, actualPhoto.getDescription());
        Assertions.assertEquals(IMAGE_URL, actualPhoto.getImageUrl());
        Assertions.assertEquals(THUMBNAIL_URL, actualPhoto.getThumbnailUrl());
        Assertions.assertEquals(FILENAME, actualPhoto.getFilename());
        Assertions.assertEquals(CONTENT_TYPE, actualPhoto.getContentType());
        Assertions.assertEquals(FILE_SIZE, actualPhoto.getFileSize());
        Assertions.assertNotNull(actualPhoto.getOwner());
        Assertions.assertEquals(LOGIN, actualPhoto.getOwner().getLogin());
        Assertions.assertEquals(PASSWORD, actualPhoto.getOwner().getPassword());
        Assertions.assertEquals(MAIL, actualPhoto.getOwner().getMail());
    }

    @Test
    void testConvertEntityToDtoNull()
    {
        Assertions.assertNull(photoService.convertEntityToDto(null));
    }

    @Test
    void testConvertDtoToEntityNull()
    {
        Assertions.assertNull(photoService.convertDtoToEntity(null));
    }

    @Test
    void testConvertEntityToDtoWithTeam()
    {
        final Team team = Team.builder()
                .title(TEAM_TITLE)
                .description(TEAM_DESCRIPTION)
                .build();
        final User owner = User.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .mail(MAIL)
                .build();
        final Photo photo = Photo.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .imageUrl(IMAGE_URL)
                .thumbnailUrl(THUMBNAIL_URL)
                .filename(FILENAME)
                .contentType(CONTENT_TYPE)
                .fileSize(FILE_SIZE)
                .owner(owner)
                .teams(Set.of(team))
                .build();

        final PhotoDto actualPhotoDto = photoService.convertEntityToDto(photo);

        Assertions.assertNotNull(actualPhotoDto);
        Assertions.assertEquals(TITLE, actualPhotoDto.title());
        Assertions.assertEquals(DESCRIPTION, actualPhotoDto.description());
        Assertions.assertEquals(IMAGE_URL, actualPhotoDto.imageUrl());
        Assertions.assertEquals(THUMBNAIL_URL, actualPhotoDto.thumbnailUrl());
        Assertions.assertEquals(FILENAME, actualPhotoDto.filename());
        Assertions.assertEquals(CONTENT_TYPE, actualPhotoDto.contentType());
        Assertions.assertEquals(FILE_SIZE, actualPhotoDto.fileSize());

        Assertions.assertNotNull(actualPhotoDto.owner());
        Assertions.assertEquals(LOGIN, actualPhotoDto.owner().login());
        Assertions.assertEquals(PASSWORD, actualPhotoDto.owner().password());
        Assertions.assertEquals(MAIL, actualPhotoDto.owner().mail());

        Assertions.assertNotNull(actualPhotoDto.teams());
        Assertions.assertEquals(1, actualPhotoDto.teams().size());
        final TeamDto actualTeamDto = actualPhotoDto.teams().iterator().next();
        Assertions.assertEquals(TEAM_TITLE, actualTeamDto.title());
        Assertions.assertEquals(TEAM_DESCRIPTION, actualTeamDto.description());
    }

    @Test
    void testConvertDtoToEntityWithTeam()
    {
        final TeamDto teamDto = TeamDto.builder()
                .title(TEAM_TITLE)
                .description(TEAM_DESCRIPTION)
                .build();
        final UserDto ownerDto = UserDto.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .mail(MAIL)
                .build();
        final PhotoDto photoDto = PhotoDto.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .imageUrl(IMAGE_URL)
                .thumbnailUrl(THUMBNAIL_URL)
                .filename(FILENAME)
                .contentType(CONTENT_TYPE)
                .fileSize(FILE_SIZE)
                .owner(ownerDto)
                .teams(Set.of(teamDto))
                .build();

        final Photo actualPhoto = photoService.convertDtoToEntity(photoDto);

        Assertions.assertNotNull(actualPhoto);
        Assertions.assertEquals(TITLE, actualPhoto.getTitle());
        Assertions.assertEquals(DESCRIPTION, actualPhoto.getDescription());
        Assertions.assertEquals(IMAGE_URL, actualPhoto.getImageUrl());
        Assertions.assertEquals(THUMBNAIL_URL, actualPhoto.getThumbnailUrl());
        Assertions.assertEquals(FILENAME, actualPhoto.getFilename());
        Assertions.assertEquals(CONTENT_TYPE, actualPhoto.getContentType());
        Assertions.assertEquals(FILE_SIZE, actualPhoto.getFileSize());

        Assertions.assertNotNull(actualPhoto.getOwner());
        Assertions.assertEquals(LOGIN, actualPhoto.getOwner().getLogin());
        Assertions.assertEquals(PASSWORD, actualPhoto.getOwner().getPassword());
        Assertions.assertEquals(MAIL, actualPhoto.getOwner().getMail());

        Assertions.assertNotNull(actualPhoto.getTeams());
        Assertions.assertEquals(1, actualPhoto.getTeams().size());
        final Team actualTeam = actualPhoto.getTeams().iterator().next();
        Assertions.assertEquals(TEAM_TITLE, actualTeam.getTitle());
        Assertions.assertEquals(TEAM_DESCRIPTION, actualTeam.getDescription());
    }
}
