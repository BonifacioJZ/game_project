package com.bonifacio.game_project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "classifications")
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    @Column
    private String name;
    @NotEmpty
    @NotBlank
    @Size(max = 10,min = 1)
    private String classification;
    @Size(max = 500)
    private String description;
    @JoinColumn(name = "classification_system_id")
    @ManyToOne(targetEntity = ClassificationSystem.class)
    private ClassificationSystem classificationSystem;
    @ManyToMany(mappedBy = "classifications")
    private List<VideoGame> videoGames;
    @CreationTimestamp
    private Instant createAt;
    @UpdateTimestamp
    private Instant updateAt;

    public void addVideoGame(VideoGame videoGame){
        if(this.getVideoGames()==null)  videoGames = new ArrayList<>();
        this.getVideoGames().add(videoGame);
        videoGame.getClassifications().add(this);
    }
}
