package com.uit.projectback.dto;

import com.uit.projectback.model.Place;
import lombok.Data;

@Data
public class FavoritePlaceResponse {

    private Integer placeId;
    private String name;
    private String description;
    private String placeType;
    private String phone;
    private String websiteUrl;
    private String openingHours;
    private Double latitude;
    private Double longitude;
    private Double minPrice;
    private Double maxPrice;

    public FavoritePlaceResponse(Place place) {
        this.placeId = place.getPlaceId();
        this.name = place.getName();
        this.description = place.getDescription();
        this.placeType = place.getPlaceType();
        this.phone = place.getPhone();
        this.websiteUrl = place.getWebsiteUrl();
        this.openingHours = place.getOpeningHours();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.minPrice = place.getMinPrice();
        this.maxPrice = place.getMaxPrice();
    }

    // Getters and setters...
}
