package com.uit.projectback.controller;

import com.uit.projectback.dto.PlaceResponseDto;
import com.uit.projectback.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@CrossOrigin("*") // Android
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

    // 🔹 Par type (RESTAURANT / EVENT / TOURISTIC)
    @GetMapping("/type/{type}")
    public List<PlaceResponseDto> getByType(@PathVariable String type) {
        return placeService.getByType(type);
    }

    // 🔹 Recherche
    @GetMapping("/search")
    public List<PlaceResponseDto> search(@RequestParam String q) {
        return placeService.search(q);
    }

    // 🔹 Détails d’un lieu
    @GetMapping("/{id}")
    public PlaceResponseDto getPlaceById(@PathVariable Integer id) {
    return placeService.getById(id);
}

}
