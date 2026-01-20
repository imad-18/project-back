package com.uit.projectback.controller;

import com.uit.projectback.model.EntityType;
import com.uit.projectback.model.FavoriteModel;
import com.uit.projectback.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin
public class FavoriteController {

    private FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<FavoriteModel> addFavorite(
            @RequestBody FavoriteModel request
    ) {
        FavoriteModel favorite = favoriteService.addFavorite(
                request.getUserId(),
                request.getEntityType(),
                request.getEntityId()
        );

        return ResponseEntity.ok(favorite);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteModel>> getFavoritesByUserId(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUserId(userId));
    }

}
