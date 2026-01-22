package com.uit.projectback.dto;

import com.uit.projectback.model.AccommodationModel;
import com.uit.projectback.model.EntityType;
import com.uit.projectback.model.TransportModel;

import java.time.LocalDateTime;

public class FavoriteResponseDto {

    private Long favoriteId;
    private EntityType entityType;
    private LocalDateTime createdAt;

    private PlaceResponseDto place;
    private AccommodationResponseDto accommodation;
    private TransportResponseDto transport;

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PlaceResponseDto getPlace() {
        return place;
    }

    public void setPlace(PlaceResponseDto place) {
        this.place = place;
    }

    public AccommodationResponseDto getAccommodation() {
        return accommodation;
    }
    public void setAccommodation(AccommodationResponseDto accommodation) {
        this.accommodation = accommodation;
    }

    public TransportResponseDto getTransport() {
        return transport;
    }

    public void setTransport(TransportResponseDto transport) {
        this.transport = transport;
    }
}

