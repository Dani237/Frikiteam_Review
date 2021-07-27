package com.frikiteam.review.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    private String reference;

    // relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private District district;

    public Place(@NotNull String name, String reference) {
        this.name = name;
        this.reference = reference;
    }

    public Place(){}
}
