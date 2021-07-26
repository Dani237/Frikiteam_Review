package com.frikiteam.review.controller;

import com.frikiteam.review.domain.model.City;
import com.frikiteam.review.domain.service.CityService;
import com.frikiteam.review.resource.CityResource;
import com.frikiteam.review.resource.SaveCityResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/countries/{countryId}/cities")
public class CitiesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CityService cityService;

    private City convertToEntity(SaveCityResource resource){
        return mapper.map(resource, City.class);
    }
    private CityResource convertToResource(City entity){
        return mapper.map(entity, CityResource.class);
    }

    @GetMapping
    @Operation(summary = "Get all cities by pages", tags = {"country-cities"})
    public Page<CityResource> getAllCities(Pageable pageable){
        List<CityResource> resources = cityService.getAllCities(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping
    @Operation(summary = "create a city by country id", tags = {"country-cities"})
    public CityResource createCity(@PathVariable Long countryId, @Valid @RequestBody SaveCityResource resource){
        City city = convertToEntity(resource);
        return convertToResource(cityService.createCity(countryId, city));
    }

    @PutMapping("{cityId}")
    @Operation(summary = "update a city", tags = {"country-cities"})
    public CityResource updateCity(@PathVariable Long cityId, @RequestBody SaveCityResource resource){
        City city = convertToEntity(resource);
        return convertToResource(cityService.updateCity(cityId,city));
    }

    @DeleteMapping("{cityId}")
    @Operation(summary = "delete a city", tags = {"country-cities"})
    public ResponseEntity<?> deleteCity(@PathVariable Long cityId)
    {
        return cityService.deleteCity(cityId);
    }

    @GetMapping("{cityId}")
    @Operation(summary = "Get a city by id", tags = {"country-cities"})
    public CityResource getCityById(@PathVariable Long cityId){
        return convertToResource(cityService.getCityById(cityId));
    }

}
