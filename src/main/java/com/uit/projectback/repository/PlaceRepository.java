package com.uit.projectback.repository;

import com.uit.projectback.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    List<Place> findByPlaceType(String placeType);

    List<Place> findByCityId(Integer cityId);

    List<Place> findByNameContainingIgnoreCase(String keyword);

    @Query("""
            SELECT p FROM Place p
            WHERE (:q IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%')))
              AND (:type IS NULL OR UPPER(p.placeType) = UPPER(:type))
              AND (:minPrice IS NULL OR p.minPrice >= :minPrice)
              AND (:maxPrice IS NULL OR p.maxPrice <= :maxPrice)
              AND (:opening IS NULL OR LOWER(p.openingHours) LIKE LOWER(CONCAT('%', :opening, '%')))
            """)
    List<Place> filterPlaces(
            @Param("q") String q,
            @Param("type") String type,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("opening") String opening);

}
