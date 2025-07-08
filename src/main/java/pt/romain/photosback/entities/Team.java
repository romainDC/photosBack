package pt.romain.photosback.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT") // Permet un texte plus long pour la description
    private String description;

    @OneToMany(mappedBy = "team")
    @EqualsAndHashCode.Exclude
    private Set<User> members;

    @ManyToMany()
    @JoinTable(
            name = "photo_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Photo> photos;
}
