package com.uit.projectback.controller;

import com.uit.projectback.dto.PlaceResponseDto;
import com.uit.projectback.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@CrossOrigin("*") // pour Android
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    // 🔹 Tous les lieux
    @GetMapping
    public List<PlaceResponseDto> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    // 🔹 Par type (ex: POPULAR, Monument, Mosquée, etc.)
    @GetMapping("/type/{type}")
    public List<PlaceResponseDto> getByType(@PathVariable String type) {
        return placeService.getByType(type);
    }

    // 🔹 Recherche par mot-clé
    @GetMapping("/search")
    public List<PlaceResponseDto> search(@RequestParam String q) {
        return placeService.search(q);
    }

    // 🔹 Filtre complet (une seule méthode, correspond à Retrofit)
    @GetMapping("/filter")
    public List<PlaceResponseDto> filterPlaces(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String opening) {
        return placeService.filter(q, type, minPrice, maxPrice, opening);
    }
}
