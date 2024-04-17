package com.bonifacio.game_project.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Java class represents a Plataform entity with various fields annotated for database mapping and
 * validation.
 */
@Entity
@Table(name = "plataforms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plataform {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    private String name;
    
    @Column
    @Size(max = 500)
    private String description;
    @NotEmpty
    @NotEmpty
    @Size(max = 4,min = 2)
    @Column(name = "guard_name",unique = true)
    private String guardName;
    @NotNull
    @Column(name = "realise_date")
    private LocalDate realiseDate;
    @CreationTimestamp
    @Column(name = "create_at")
    private Instant createAt;
    @UpdateTimestamp
    @Column(name = "update_at")
    private Instant updateAt;
}
