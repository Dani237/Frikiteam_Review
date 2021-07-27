package com.frikiteam.review.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlaceResource {
    private Long id;
    @NotNull
    private String name;

    private String reference;
}

//ver la relacion inversa