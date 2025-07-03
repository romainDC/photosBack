package pt.romain.photosback.service;

import org.springframework.stereotype.Service;
import pt.romain.photosback.dto.PhotoDto;
import pt.romain.photosback.entities.Photo;

import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService
{
    private final CategoryService categoryService;
    private final UserService userService;
    private final TeamService teamService;

    public PhotoServiceImpl(CategoryService categoryService, UserService userService, TeamService teamService)
    {
        this.categoryService = categoryService;
        this.userService = userService;
        this.teamService = teamService;
    }

    @Override
    public PhotoDto convertEntityToDto(Photo photo)
    {
        if (photo == null)
            return null;
        return PhotoDto.builder()
                .id(photo.getId())
                .title(photo.getTitle())
                .description(photo.getDescription())
                .imageUrl(photo.getImageUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .filename(photo.getFilename())
                .contentType(photo.getContentType())
                .filename(photo.getFilename())
                .uploadedAt(photo.getUploadedAt())
                .category(categoryService.convertEntityToDto(photo.getCategory()))
                .owner(userService.convertEntityToDto(photo.getOwner()))
                .teams(photo.getTeams()
                        .stream()
                        .map(teamService::convertEntityToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Photo convertDtoToEntity(PhotoDto photoDto)
    {
        if (photoDto == null)
            return null;
        return Photo.builder()
                .id(photoDto.id())
                .title(photoDto.title())
                .description(photoDto.description())
                .imageUrl(photoDto.imageUrl())
                .thumbnailUrl(photoDto.thumbnailUrl())
                .filename(photoDto.filename())
                .contentType(photoDto.contentType())
                .fileSize(photoDto.fileSize())
                .uploadedAt(photoDto.uploadedAt())
                .category(categoryService.convertDtoToEntity(photoDto.category()))
                .owner(userService.convertDtoToEntity(photoDto.owner()))
                .teams(photoDto.teams()
                        .stream()
                        .map(teamService::convertDtoToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
