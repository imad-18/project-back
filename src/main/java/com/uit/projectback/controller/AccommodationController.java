package com.uit.projectback.controller;

import com.uit.projectback.model.AccommodationModel;
import com.uit.projectback.service.AccommodationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accommodations")
@CrossOrigin("*") // Android
public class AccommodationController {
    private AccommodationService accommodationService;
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<AccommodationModel> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping("/{id}")
    public AccommodationModel getAccommodationById(@PathVariable Integer id) {
        return accommodationService.getAccommodationById(id);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getAccommodationsByType(@PathVariable String type) {
        List<AccommodationModel> accommodations = accommodationService.getAccommodationsByType(type);

        if (accommodations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No accommodations found for type: " + type);
        }

        return ResponseEntity.ok(accommodations);
    }
}
