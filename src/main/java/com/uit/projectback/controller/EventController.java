package com.uit.projectback.controller;


import com.uit.projectback.dto.EventResponseDto;
import com.uit.projectback.model.EventModel;
import com.uit.projectback.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin("*")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // get all events
    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        List<EventResponseDto> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No Events found for this moment: ");
        }
        else {
            return ResponseEntity.ok(events);
        }
    }

    /*@GetMapping("/getallevents")
    public List<EventModel> getEvents() {
        return eventService.getAllEvents();
    }*/

}
