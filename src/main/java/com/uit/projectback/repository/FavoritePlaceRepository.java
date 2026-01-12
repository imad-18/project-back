package com.uit.projectback.repository;

//FavoritePlaceRepository.java

import com.uit.projectback.model.FavoritePlace;
import com.uit.projectback.model.FavoritePlaceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritePlaceRepository extends JpaRepository<FavoritePlace, FavoritePlaceId> {

    @Query("SELECT fp FROM FavoritePlace fp WHERE fp.id.userId = :userId")
    List<FavoritePlace> findByIdUserId(@Param("userId") Integer userId);

    boolean existsById(FavoritePlaceId id);
}
