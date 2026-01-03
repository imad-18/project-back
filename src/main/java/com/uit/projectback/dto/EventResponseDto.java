package com.uit.projectback.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventResponseDto {

    private Integer eventId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String eventType;
    private String imgPath;
    private Boolean isSpecial;
    private String websiteUrl;
    private String cityName;
}
