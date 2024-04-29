package com.bonifacio.game_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "classification_systems")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassificationSystem {

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
    private String descriptions;
    @NotEmpty
    @NotBlank
    @Size(max = 250)
    @Column()
    private String places;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "classification_system_id")
    private Set<Classification> classificationList;
    @CreationTimestamp
    private Instant createAt;
    @UpdateTimestamp
    private Instant updateAt;

}
