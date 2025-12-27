package com.uit.projectback.repository;

import com.uit.projectback.model.AccommodationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<AccommodationModel, Integer> {
    List<AccommodationModel> findAccommodationByType(String type);
    //Spring Data JPA automatically generates the SQL query based on the method name convention
    //SELECT * FROM accommodations WHERE type = ?;
}
