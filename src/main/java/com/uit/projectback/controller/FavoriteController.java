package com.uit.projectback.controller;

import com.uit.projectback.dto.FavoriteRequestDto;
import com.uit.projectback.dto.FavoriteResponseDto;
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

    /*@PostMapping
    public ResponseEntity<FavoriteModel> addFavorite(
            @RequestBody FavoriteModel request
    ) {
        FavoriteModel favorite = favoriteService.addFavorite(
                request.getUserId(),
                request.getEntityType(),
                request.getEntityId()
        );

        return ResponseEntity.ok(favorite);
    }*/

    @PostMapping
    public ResponseEntity<FavoriteResponseDto> addFavorite(
            @RequestBody FavoriteRequestDto request
    ) {
        FavoriteResponseDto response = favoriteService.addFavorite(
                request.getUserId(),
                request.getEntityType(),
                request.getEntityId()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteResponseDto>> getFavoritesByUserId(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUserId(userId));
    }

    @GetMapping("/{userId}/{entityType}")
    public ResponseEntity<List<FavoriteModel>> getFavoritesByUserIdAndEntityType(
            @PathVariable Long userId,
            @PathVariable EntityType entityType
    ) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUserIdAndEntityType(userId, entityType));
    }

    @DeleteMapping("/{userId}/{entityType}/{entityId}")
    public ResponseEntity<Void> removeFavorite(
            @PathVariable Long userId,
            @PathVariable EntityType entityType,
            @PathVariable Long entityId
    ) {
        favoriteService.removeFavorite(userId, entityType, entityId);
        return ResponseEntity.noContent().build(); // 204 No Content
        //return ResponseEntity.ok("Item removed successfully"); // 200 OK with message,
        //N.B: ResponseEntity<String> if you want to return a message
    }

}
