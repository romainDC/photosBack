package pt.romain.photosback.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(name = "created_at", nullable = false, updatable = false) // Date de création, non modifiable
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "owner")
    private Set<Photo> photos;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // Plusieurs utilisateurs peuvent appartenir à un groupe
    @JoinColumn(name = "team_id") // Colonne de clé étrangère dans la table photos
    private Team team;

    @PrePersist // Méthode exécutée avant la persistance de l'entité
    protected void onCreate()
    {
        createdAt = LocalDateTime.now(); // Définit la date de création lors de la première sauvegarde
    }

}
