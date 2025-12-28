package com.uit.projectback.service;

import com.uit.projectback.model.TransportModel;
import com.uit.projectback.repository.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private TransportRepository transportRepository;

    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public List<TransportModel> getAllTransports() {
        return transportRepository.findAll();
    }

    public List<TransportModel> getTransportsByType(String type) {
        return transportRepository.findByType(type);
    }
}
