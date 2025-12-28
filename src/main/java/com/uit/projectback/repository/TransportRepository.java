package com.uit.projectback.repository;

import com.uit.projectback.model.TransportModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportRepository extends JpaRepository<TransportModel, Integer> {
    List<TransportModel> findByType(String type);
}
