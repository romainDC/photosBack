package pt.romain.photosback.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pt.romain.photosback.dto.PhotoDto;
import pt.romain.photosback.dto.TeamDto;
import pt.romain.photosback.entities.Photo;
import pt.romain.photosback.entities.Team;
import pt.romain.photosback.repository.PhotoRepository;
import pt.romain.photosback.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService
{
    private final CategoryService categoryService;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TeamService teamService;

    public PhotoServiceImpl(CategoryService categoryService, PhotoRepository photoRepository, UserRepository userRepository, UserService userService, TeamService teamService)
    {
        this.categoryService = categoryService;
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.teamService = teamService;
    }

    @Override
    public PhotoDto convertEntityToDto(Photo photo)
    {
        if (photo == null)
            return null;
        Set<TeamDto> teamDtos = null;
        if (photo.getTeams() != null) {
            teamDtos = photo.getTeams()
                    .stream()
                    .map(teamService::convertEntityToDto)
                    .collect(Collectors.toSet());
        }
        return PhotoDto.builder()
                .id(photo.getId())
                .title(photo.getTitle())
                .description(photo.getDescription())
                .imageUrl(photo.getImageUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .filename(photo.getFilename())
                .contentType(photo.getContentType())
                .fileSize(photo.getFileSize())
                .uploadedAt(photo.getUploadedAt())
                .category(categoryService.convertEntityToDto(photo.getCategory()))
                .owner(userService.convertEntityToDto(photo.getOwner()))
                .teams(teamDtos)
                .build();
    }

    @Override
    public Photo convertDtoToEntity(PhotoDto photoDto)
    {
        if (photoDto == null)
            return null;
        Set<Team> teams = null;
        if (photoDto.teams() != null) {
            teams = photoDto.teams()
                    .stream()
                    .map(teamService::convertDtoToEntity)
                    .collect(Collectors.toSet());
        }
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
                .teams(teams)
                .build();
    }

    @Override
    public PhotoDto uploadPhoto(MultipartFile file, String title, String description)
    {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload empty file.");
        }
        if (!"image/jpeg".equals(file.getContentType())) {
            throw new IllegalArgumentException("Only JPEG images are allowed.");
        }

        // 1. Téléverser l'image vers le service de stockage cloud
        //String imageUrl = cloudStorageService.uploadFile(file);

        // 2. Créer l'entité Photo et sauvegarder les métadonnées (y compris l'URL) en base de données
        Photo photo = new Photo();
        photo.setOwner(userRepository.findAll().getFirst());
        photo.setTitle(title);
        photo.setDescription(description);
        photo.setFilename(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setFileSize(file.getSize());
        photo.setImageUrl(""); // Stocke l'URL de l'image ici
        photo.setThumbnailUrl(""); // Si tu génères des miniatures

        return convertEntityToDto(photoRepository.save(photo));
    }
}
