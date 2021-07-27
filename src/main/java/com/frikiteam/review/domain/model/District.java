package com.frikiteam.review.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String reference;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private City city;

    public District(){}

    public District(String name, String reference) {
        this.name = name;
        this.reference = reference;
    }
}
