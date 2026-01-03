package com.uit.projectback.service;

import com.uit.projectback.dto.EventResponseDto;
import com.uit.projectback.model.EventModel;
import com.uit.projectback.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Get all events
    /*public List<EventModel> getAllEvents() {
        *//*return eventRepository.findAll();*//*
        return eventRepository.findAllByOrderByIsSpecialDesc();
    }*/
    public List<EventResponseDto> getAllEvents() {
        /*return eventRepository.findAll();*/
        return eventRepository.findAllByOrderByIsSpecialDesc()
                .stream()
                .map(event -> {
                    EventResponseDto dto = new EventResponseDto();
                    dto.setEventId(event.getEventId());
                    dto.setName(event.getName());
                    dto.setDescription(event.getDescription());
                    dto.setStartDate(event.getStartDate());
                    dto.setEndDate(event.getEndDate());
                    dto.setEventType(event.getEventType());
                    dto.setImgPath(event.getImgPath());
                    dto.setIsSpecial(event.getIsSpecial());
                    dto.setWebsiteUrl(event.getWebsiteUrl());

                    if (event.getCity() != null) {
                        dto.setCityName(event.getCity().getName());
                    }

                    return dto;
                })
                .toList();
    }
}
