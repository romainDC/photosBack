package pt.romain.photosback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pt.romain.photosback.dto.PhotoDto;
import pt.romain.photosback.service.PhotoService;

@RestController
@RequestMapping("/api/photos")
public class PhotoController
{

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService)
    {
        this.photoService = photoService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description)
    {
        try {
            PhotoDto uploadedPhoto = photoService.uploadPhoto(file, title, description);
            return ResponseEntity.status(HttpStatus.CREATED).body(uploadedPhoto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
