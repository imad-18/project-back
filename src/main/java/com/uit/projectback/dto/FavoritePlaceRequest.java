package com.uit.projectback.dto;

//FavoritePlaceRequest.java
public class FavoritePlaceRequest {

    private Integer userId;
    private Integer placeId;

    public FavoritePlaceRequest() {
    }

    public FavoritePlaceRequest(Integer userId, Integer placeId) {
        this.userId = userId;
        this.placeId = placeId;
    }

    // ==================
    // GETTERS & SETTERS
    // ==================

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }
}