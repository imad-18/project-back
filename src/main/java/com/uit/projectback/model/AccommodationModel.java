package com.uit.projectback.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accommodation_provider")
@Data
public class AccommodationModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "accommodation_provider_seq"
    )
    @SequenceGenerator(
            name = "accommodation_provider_seq",
            sequenceName = "\"AccommodationProvider_id_seq\"",
            allocationSize = 1
    )
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 30)
    private String type;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "website_url", columnDefinition = "text")
    private String websiteUrl;

    @Column(name = "logo_url", columnDefinition = "text")
    private String logoUrl;
}

