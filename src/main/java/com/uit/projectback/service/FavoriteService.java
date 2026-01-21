package com.uit.projectback.service;

import com.uit.projectback.model.EntityType;
import com.uit.projectback.model.FavoriteModel;
import com.uit.projectback.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<FavoriteModel> getFavoritesByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public List<FavoriteModel> getFavoritesByUserIdAndEntityType(Long userId, EntityType entityType) {
        return favoriteRepository.findByUserIdAndEntityType(userId, entityType);
    }

    public void removeFavorite(Long userId, EntityType entityType, Long entityId) {
        favoriteRepository.deleteByUserIdAndEntityTypeAndEntityId(userId, entityType, entityId);
    }
}
