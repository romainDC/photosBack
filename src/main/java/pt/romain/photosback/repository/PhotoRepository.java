package pt.romain.photosback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.romain.photosback.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>
{
}
