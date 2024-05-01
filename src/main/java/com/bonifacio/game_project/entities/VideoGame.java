package com.bonifacio.game_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "video_games")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class VideoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    @Column
    private String name;
    @Size(max = 500)
    @Column
    private String description;
    @NotNull
    @Column(name = "realise_date")
    private LocalDate realiseDate;
    //private Set<Gender> genders;
    @JsonIgnore
    @ManyToMany(targetEntity = Classification.class, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.LAZY)
    @JoinTable(name = "video_game_classification",joinColumns={@JoinColumn(name = "fk_video_game")},
            inverseJoinColumns = {@JoinColumn(name = "fk_classification")} )
    private Set<Classification> classifications = new HashSet<>();
    @Column
    private String image;
    @Column(name = "create_at")
    @CreationTimestamp
    private Instant createAt;
    @Column(name = "update_at")
    @UpdateTimestamp
    private Instant updateAt;
}
