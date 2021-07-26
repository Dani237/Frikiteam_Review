package com.frikiteam.review.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String reference;

    // relationship
    // too much cities can be in a country
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;



    public City(){}

    public City(String name, String reference) {
        this.name = name;
        this.reference = reference;
    }
}
