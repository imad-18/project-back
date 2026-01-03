package com.uit.projectback.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "event")
@Data
public class EventModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_seq"
    )
    @SequenceGenerator(
            name = "event_seq",
            sequenceName = "\"Event_event_id_seq\"",
            allocationSize = 1
    )
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "event_type", nullable = false, length = 50)
    private String eventType;

    @Column(name = "place_id")
    private Integer placeId;

    // ✅ RELATIONSHIP
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityModel city;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "is_special")
    private Boolean isSpecial = false;

    @Column(name = "website_url")
    private String websiteUrl;
}
