package pt.romain.photosback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.romain.photosback.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
