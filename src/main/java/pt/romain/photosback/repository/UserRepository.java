package pt.romain.photosback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.romain.photosback.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * Spring Data JPA infère la requête SQL à partir du nom de la méthode.
     *
     * @param username Le nom d'utilisateur à rechercher.
     * @return Un Optional contenant l'utilisateur s'il est trouvé, vide sinon.
     */
    Optional<User> findByLogin(String username);

    /**
     * Recherche un utilisateur par son email.
     *
     * @param email L'adresse email à rechercher.
     * @return Un Optional contenant l'utilisateur s'il est trouvé, vide sinon.
     */
    Optional<User> findByMail(String email);
}
