package com.uit.projectback.repository;

import com.uit.projectback.model.EntityType;
import com.uit.projectback.model.FavoriteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteModel, Long> {

    List<FavoriteModel> findByUserId(Long userId);

    List<FavoriteModel> findByUserIdAndEntityType(Long userId, EntityType entityType);

    Optional<FavoriteModel> findByUserIdAndEntityTypeAndEntityId(
            Long userId,
            EntityType entityType,
            Long entityId
    );

    void deleteByUserIdAndEntityTypeAndEntityId(
            Long userId,
            EntityType entityType,
            Long entityId
    );
}
