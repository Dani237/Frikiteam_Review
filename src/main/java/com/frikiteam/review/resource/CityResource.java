package com.frikiteam.review.resource;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CityResource {
    private Long id;
    @NotNull
    private String name;

    private String reference;

    //se pone el resoruce de la variable que esta en la otra clase
    //en el save no se pone esto
    private CountryResource country;
}
