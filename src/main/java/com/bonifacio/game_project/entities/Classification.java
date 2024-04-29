package com.bonifacio.game_project.entities;

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
import java.util.UUID;

@Entity
@Table(name = "classifications")
@AllArgsConstructor
@Data
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
    @CreationTimestamp
    private Instant createAt;
    @UpdateTimestamp
    private Instant updateAt;
}
