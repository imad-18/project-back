package com.uit.projectback.model;

//FavoritePlaceId.java

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavoritePlaceId implements Serializable {

    private Integer userId;
    private Integer placeId;

    public FavoritePlaceId() {
    }

    public FavoritePlaceId(Integer userId, Integer placeId) {
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

    // ==================
    // EQUALS & HASHCODE
    // ==================

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FavoritePlaceId that = (FavoritePlaceId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(placeId, that.placeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, placeId);
    }
}