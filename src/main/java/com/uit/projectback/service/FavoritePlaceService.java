package com.uit.projectback.service;

//FavoritePlaceService.java

import com.uit.projectback.dto.FavoritePlaceRequest;
import com.uit.projectback.model.FavoritePlace;
import com.uit.projectback.model.FavoritePlaceId;
import com.uit.projectback.model.Place;
import com.uit.projectback.model.User;
import com.uit.projectback.repository.FavoritePlaceRepository;
import com.uit.projectback.repository.PlaceRepository;
import com.uit.projectback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritePlaceService {

    @Autowired
    private FavoritePlaceRepository favoritePlaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public List<FavoritePlace> getFavoritesByUserId(Integer userId) {
        return favoritePlaceRepository.findByIdUserId(userId);
    }

    public FavoritePlace addFavorite(FavoritePlaceRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        Optional<Place> placeOpt = placeRepository.findById(request.getPlaceId());

        if (userOpt.isPresent() && placeOpt.isPresent()) {
            FavoritePlaceId id = new FavoritePlaceId(request.getUserId(), request.getPlaceId());
            if (!favoritePlaceRepository.existsById(id)) {
                FavoritePlace favorite = new FavoritePlace();
                favorite.setId(id);
                favorite.setUser(userOpt.get());
                favorite.setPlace(placeOpt.get());
                return favoritePlaceRepository.save(favorite);
            }
        }
        return null;
    }

    public void removeFavorite(Integer userId, Integer placeId) {
        FavoritePlaceId id = new FavoritePlaceId(userId, placeId);
        favoritePlaceRepository.deleteById(id);
    }
}