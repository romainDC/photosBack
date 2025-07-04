package pt.romain.photosback.service;

import org.springframework.web.multipart.MultipartFile;
import pt.romain.photosback.dto.PhotoDto;
import pt.romain.photosback.entities.Photo;

public interface PhotoService
{
    PhotoDto convertEntityToDto(Photo photo);

    Photo convertDtoToEntity(PhotoDto photoDto);

    /**
     * Téléverse une nouvelle photo vers le stockage cloud et enregistre ses métadonnées en DB.
     *
     * @param file        Le fichier MultipartFile envoyé par le client.
     * @param title       Le titre de la photo.
     * @param description La description de la photo.
     * @return L'objet PhotoDto sauvegardé en base de données avec son URL.
     * @throws IllegalArgumentException si le fichier est vide ou n'est pas une image JPEG.
     */
    PhotoDto uploadPhoto(MultipartFile file, String title, String description);
}
