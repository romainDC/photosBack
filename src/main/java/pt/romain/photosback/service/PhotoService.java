package pt.romain.photosback.service;

import pt.romain.photosback.dto.PhotoDto;
import pt.romain.photosback.entities.Photo;

public interface PhotoService
{
    PhotoDto convertEntityToDto(Photo photo);

    Photo convertDtoToEntity(PhotoDto photoDto);
}
