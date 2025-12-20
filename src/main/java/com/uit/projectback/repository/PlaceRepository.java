package com.uit.projectback.repository;

import com.uit.projectback.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    List<Place> findByPlaceType(String placeType);

    List<Place> findByCityId(Integer cityId);

    List<Place> findByNameContainingIgnoreCase(String keyword);
}
