package com.frikiteam.review.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    // relation inverse
    //A country can have a lot of cities, inverse code
    //the cascade is for to eliminate correctly data of DB
    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE)
    private List<City> cities = new ArrayList<>();

    public Country(){}

    public Country(String name) {
        this.name = name;
    }
}
