package com.uit.projectback.service;

import com.uit.projectback.dto.PlaceResponseDto;
import com.uit.projectback.model.Place;
import com.uit.projectback.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<PlaceResponseDto> getAllPlaces() {
        return placeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<PlaceResponseDto> getByType(String type) {
        return placeRepository.findByPlaceType(type)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<PlaceResponseDto> search(String keyword) {
        return placeRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    // FILTRE
    public List<PlaceResponseDto> filter(
            String q,
            String type,
            Double minPrice,
            Double maxPrice,
            String opening) {
        return placeRepository.filterPlaces(
                emptyToNull(q),
                emptyToNull(type),
                minPrice,
                maxPrice,
                emptyToNull(opening))
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    // 🔧 UTIL
    private String emptyToNull(String v) {
        return (v == null || v.isBlank() || v.equalsIgnoreCase("Tous")) ? null : v;
    }

    private PlaceResponseDto mapToDto(Place place) {
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
}
