package com.uit.projectback.service;

import com.uit.projectback.controller.AccommodationController;
import com.uit.projectback.dto.*;
import com.uit.projectback.model.*;
import com.uit.projectback.repository.AccommodationRepository;
import com.uit.projectback.repository.FavoriteRepository;
import com.uit.projectback.repository.PlaceRepository;
import com.uit.projectback.repository.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private PlaceRepository placeRepository;
    private AccommodationRepository accommodationRepository;
    private TransportRepository transportRepository;

    public FavoriteService(FavoriteRepository favoriteRepository,
                            PlaceRepository placeRepository,
                           AccommodationRepository accommodationRepository,
                           TransportRepository transportRepository) {
        this.favoriteRepository = favoriteRepository;
        this.placeRepository = placeRepository;
        this.accommodationRepository = accommodationRepository;
        this.transportRepository = transportRepository;
    }

    /*public FavoriteModel addFavorite(Long userId, EntityType entityType, Long entityId) {

        return favoriteRepository
                .findByUserIdAndEntityTypeAndEntityId(userId, entityType, entityId)
                .orElseGet(() ->
                        favoriteRepository.save(
                                new FavoriteModel(userId, entityType, entityId)
                        )
                );
    }*/

    public FavoriteResponseDto addFavorite(Long userId, EntityType entityType, Long entityId) {

        FavoriteModel favorite = favoriteRepository
                .findByUserIdAndEntityTypeAndEntityId(userId, entityType, entityId)
                .orElseGet(() ->
                        favoriteRepository.save(
                                new FavoriteModel(userId, entityType, entityId)
                        )
                );

        // Build response DTO
        FavoriteResponseDto dto = new FavoriteResponseDto();
        dto.setFavoriteId(favorite.getId());
        dto.setEntityType(favorite.getEntityType());
        dto.setCreatedAt(favorite.getCreatedAt());

        switch (favorite.getEntityType()) {

            case PLACE -> {
                Place place = placeRepository
                        .findById(entityId.intValue())
                        .orElseThrow(() -> new RuntimeException("Place not found"));
                dto.setPlace(mapPlaceToDto(place));
            }

            case ACCOMMODATION -> {
                AccommodationModel acc = accommodationRepository
                        .findById(entityId.intValue())
                        .orElseThrow(() -> new RuntimeException("Accommodation not found"));
                dto.setAccommodation(mapAccommodationToDto(acc));
            }

            case TRANSPORT -> {
                TransportModel transport = transportRepository
                        .findById(entityId.intValue())
                        .orElseThrow(() -> new RuntimeException("Transport not found"));
                dto.setTransport(mapTransportToDto(transport));
            }
        }

        return dto;
    }

    public List<FavoriteResponseDto> getFavoritesByUserId(Long userId) {

        List<FavoriteModel> favorites = favoriteRepository.findByUserId(userId);
        List<FavoriteResponseDto> responses = new ArrayList<>();

        for (FavoriteModel fav : favorites) {

            FavoriteResponseDto dto = new FavoriteResponseDto();
            dto.setFavoriteId(fav.getId());
            dto.setEntityType(fav.getEntityType());
            dto.setCreatedAt(fav.getCreatedAt());

            switch (fav.getEntityType()) {

                case PLACE -> {
                    Place place = placeRepository
                            .findById(fav.getEntityId().intValue())
                            .orElseThrow(() -> new RuntimeException("Place not found"));

                    dto.setPlace(mapPlaceToDto(place));
                }

                case ACCOMMODATION -> {
                    AccommodationModel acc = accommodationRepository
                            .findById(fav.getEntityId().intValue())
                            .orElseThrow(() -> new RuntimeException("Accommodation not found"));

                    dto.setAccommodation(mapAccommodationToDto(acc));
                }

                case TRANSPORT -> {
                    TransportModel transport = transportRepository
                            .findById(fav.getEntityId().intValue())
                            .orElseThrow(() -> new RuntimeException("Transport not found"));

                    dto.setTransport(mapTransportToDto(transport));
                }
            }

            responses.add(dto);
        }

        return responses;
    }

    private PlaceResponseDto mapPlaceToDto(Place place) {
        PlaceResponseDto dto = new PlaceResponseDto();
        dto.setPlaceId(place.getPlaceId());
        dto.setName(place.getName());
        dto.setPlaceType(place.getPlaceType());
        dto.setCityId(place.getCityId());
        dto.setLatitude(place.getLatitude());
        dto.setLongitude(place.getLongitude());
        dto.setMinPrice(place.getMinPrice());
        dto.setMaxPrice(place.getMaxPrice());
        dto.setOpeningHours(place.getOpeningHours());
        dto.setPhone(place.getPhone());
        dto.setWebsiteUrl(place.getWebsiteUrl());
        dto.setDescription(place.getDescription());
        return dto;
    }

    private AccommodationResponseDto mapAccommodationToDto(AccommodationModel acc) {
        return new AccommodationResponseDto(
                acc.getId(),
                acc.getName(),
                acc.getType(),
                acc.getDescription(),
                acc.getWebsiteUrl(),
                acc.getLogoUrl()
        );
    }

    private TransportResponseDto mapTransportToDto(TransportModel transport) {
        return new TransportResponseDto(
                transport.getId(),
                transport.getName(),
                transport.getType(),
                transport.getDescription(),
                transport.getWebsiteUrl(),
                transport.getLogoUrl()
        );
    }


    public List<FavoriteModel> getFavoritesByUserIdAndEntityType(Long userId, EntityType entityType) {
        return favoriteRepository.findByUserIdAndEntityType(userId, entityType);
    }

    public void removeFavorite(Long userId, EntityType entityType, Long entityId) {
        favoriteRepository.deleteByUserIdAndEntityTypeAndEntityId(userId, entityType, entityId);
    }
}
