package pt.romain.photosback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.romain.photosback.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long>
{
}
