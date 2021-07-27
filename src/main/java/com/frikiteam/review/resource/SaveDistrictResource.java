package com.frikiteam.review.resource;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class SaveDistrictResource {
    @NotNull
    private String name;

    private String reference;
}
