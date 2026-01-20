package com.uit.projectback.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(
        name = "favorite",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "entity_type", "entity_id"})
        }
)

public class FavoriteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private EntityType entityType;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // ✅ Custom constructor (what you actually need)
    public FavoriteModel(Long userId, EntityType entityType, Long entityId) {
        this.userId = userId;
        this.entityType = entityType;
        this.entityId = entityId;
    }
}