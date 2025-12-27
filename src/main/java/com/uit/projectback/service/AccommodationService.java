package com.uit.projectback.service;

import com.uit.projectback.model.AccommodationModel;
import com.uit.projectback.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {

    private AccommodationRepository accommodationRepository;

    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public List<AccommodationModel> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public AccommodationModel getAccommodationById(Integer id) {
        return accommodationRepository.findById(id).orElse(null);
    }

    public List<AccommodationModel> getAccommodationsByType(String type) {
        return accommodationRepository.findAccommodationByType(type);
    }
}
