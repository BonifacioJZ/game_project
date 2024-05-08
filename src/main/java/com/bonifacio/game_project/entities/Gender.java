package com.bonifacio.game_project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.List;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "genders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    private String name;
    @ManyToMany(mappedBy = "genders")
    private List<VideoGame> videoGames;
    @Column
    @Size(max = 500)
    private String description;
    @Column(name = "create_at")
    @CreationTimestamp
    private Instant createAt;
    @Column(name = "update_at")
    @UpdateTimestamp
    private Instant updateAt;
}
