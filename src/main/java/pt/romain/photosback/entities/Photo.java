package pt.romain.photosback.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Photo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // Permet un texte plus long pour la description
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(nullable = false)
    private String filename; // Nom original du fichier (e.g., my_image.jpeg)

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "uploaded_at", nullable = false, updatable = false)
    private LocalDateTime uploadedAt;

    @ManyToOne()
    // Plusieurs photos peuvent appartenir à une catégorie
    @JoinColumn(name = "category_id") // Colonne de clé étrangère dans la table photos
    private Category category;

    @ManyToOne()
    // Plusieurs photos peuvent appartenir à un utilisateur
    @JoinColumn(name = "owner_id", nullable = false) // Colonne de clé étrangère dans la table photos
    private User owner;

    @ManyToMany(mappedBy = "photos")
    private Set<Team> teams;

    @PrePersist
    protected void onCreate()
    {
        uploadedAt = LocalDateTime.now();
    }
}
