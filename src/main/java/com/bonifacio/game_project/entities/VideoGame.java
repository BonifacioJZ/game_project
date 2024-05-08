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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "video_games")
@Data
@AllArgsConstructor
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
    @JsonIgnore
    @ManyToMany(targetEntity = Gender.class,cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    },fetch = FetchType.LAZY)
    @JoinTable(name = "video_game_genders",joinColumns = {@JoinColumn(name = "fk_video_game")},
    inverseJoinColumns = {@JoinColumn(name = "fk_gender")})
    private List<Gender> genders;
    @JsonIgnore
    @ManyToMany(targetEntity = Classification.class, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.LAZY)
    @JoinTable(name = "video_game_classification",joinColumns={@JoinColumn(name = "fk_video_game")},
            inverseJoinColumns = {@JoinColumn(name = "fk_classification")} )
    private List<Classification> classifications;
    @JsonIgnore
    @ManyToMany(targetEntity = Plataform.class,cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    },fetch = FetchType.LAZY)
    @JoinTable(name = "video_game_platform",joinColumns = {@JoinColumn(name = "fk_video_game")},
    inverseJoinColumns = {@JoinColumn(name = "fk_platform")})
    private List<Plataform> platforms;
    @Column
    @Lob
    private String image;
    @Column(name = "create_at")
    @CreationTimestamp
    private Instant createAt;
    @Column(name = "update_at")
    @UpdateTimestamp
    private Instant updateAt;

    public VideoGame(){

    }

    public void addClassification(Classification classification){
        if(this.classifications == null){
            classifications = new ArrayList<>();
        }
        this.classifications.add(classification);
        classification.getVideoGames().add(this);
    }

}
