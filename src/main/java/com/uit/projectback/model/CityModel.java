package com.uit.projectback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "city")
@Data
public class CityModel {

    @Id
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "name")
    private String name;

    @Column(name = "region")
    private String region;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "description")
    private String description;
}

