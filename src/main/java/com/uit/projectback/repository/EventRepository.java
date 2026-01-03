package com.uit.projectback.repository;

import com.uit.projectback.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, Integer> {
    // ORDER BY is_special DESC → true first, false after
    List<EventModel> findAllByOrderByIsSpecialDesc();
}
