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
                .collect(Collectors.toList());
    }

    public List<PlaceResponseDto> getByType(String type) {
        return placeRepository.findByPlaceType(type)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PlaceResponseDto> search(String keyword) {
        return placeRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
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
        return dto;
    }
}
