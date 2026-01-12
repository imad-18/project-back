package com.uit.projectback.controller;

import com.uit.projectback.dto.FavoritePlaceRequest;
import com.uit.projectback.dto.FavoritePlaceResponse;
import com.uit.projectback.model.FavoritePlace;
import com.uit.projectback.service.FavoritePlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin("*")
public class FavoritePlaceController {

    @Autowired
    private FavoritePlaceService favoritePlaceService;

    /*@GetMapping("/user/{userId}")
    public List<FavoritePlace> getFavoritesByUser(@PathVariable Integer userId) {
        return favoritePlaceService.getFavoritesByUserId(userId);
    }*/

    @GetMapping("/user/{userId}")
    public List<FavoritePlaceResponse> getFavoritesByUser(@PathVariable Integer userId) {
        List<FavoritePlace> favorites = favoritePlaceService.getFavoritesByUserId(userId);
        return favorites.stream()
                .map(fp -> new FavoritePlaceResponse(fp.getPlace()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public FavoritePlace addFavorite(@RequestBody FavoritePlaceRequest request) {
        return favoritePlaceService.addFavorite(request);
    }

    @DeleteMapping("/user/{userId}/place/{placeId}")
    public void removeFavorite(@PathVariable Integer userId, @PathVariable Integer placeId) {
        favoritePlaceService.removeFavorite(userId, placeId);
    }
}