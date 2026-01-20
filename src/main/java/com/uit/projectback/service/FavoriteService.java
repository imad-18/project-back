package com.uit.projectback.service;

import com.uit.projectback.model.EntityType;
import com.uit.projectback.model.FavoriteModel;
import com.uit.projectback.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public FavoriteModel addFavorite(Long userId, EntityType entityType, Long entityId) {

        return favoriteRepository
                .findByUserIdAndEntityTypeAndEntityId(userId, entityType, entityId)
                .orElseGet(() ->
                        favoriteRepository.save(
                                new FavoriteModel(userId, entityType, entityId)
                        )
                );
    }
}
