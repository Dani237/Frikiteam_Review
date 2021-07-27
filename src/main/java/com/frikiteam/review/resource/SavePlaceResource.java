package com.frikiteam.review.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SavePlaceResource {
    @NotNull
    private String name;

    private String reference;
}
