package com.uit.projectback.controller;

import com.uit.projectback.model.TransportModel;
import com.uit.projectback.service.TransportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transports")
@CrossOrigin("*")
public class TransportController {

    private TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping
    public List<TransportModel> getAllTransports() {
        return transportService.getAllTransports();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getTransportsByType(@PathVariable String type) {
        List<TransportModel> transports =  transportService.getTransportsByType(type);

        if (transports.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No transports found for type: " + type);
        }
        else {
            return ResponseEntity.ok(transports);
        }
    }
}
