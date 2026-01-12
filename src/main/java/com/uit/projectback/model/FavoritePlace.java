package com.uit.projectback.model;

//FavoritePlace.java
import jakarta.persistence.*;

@Entity
@Table(name = "favorite_place")
public class FavoritePlace {

    @EmbeddedId
    private FavoritePlaceId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("placeId")
    @JoinColumn(name = "place_id")
    private Place place;

    // ==================
    // GETTERS & SETTERS
    // ==================

    public FavoritePlaceId getId() {
        return id;
    }

    public void setId(FavoritePlaceId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}