package com.frikiteam.review.controller;

import com.frikiteam.review.domain.model.Country;
import com.frikiteam.review.domain.service.CountryService;
import com.frikiteam.review.resource.CountryResource;
import com.frikiteam.review.resource.SaveCountryResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api")
public class CountriesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CountryService countryService;
    //Conversiones
    private Country convertToEntity(SaveCountryResource resource){
        return mapper.map(resource,Country.class);
    }
    private CountryResource convertToResource(Country entity){
        return mapper.map(entity,CountryResource.class);
    }

    @Operation(summary = "Get all countries by pages", description = "Get all countries", tags = {"countries"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the Lesson Type saved",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/countries")
    public Page<CountryResource> getAllCountries(Pageable pageable){
        List<CountryResource> resources = countryService.getAllCountries(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping("/countries")
    @Operation(summary = "create a country", tags = {"countries"})
    public CountryResource createCountry(@Valid @RequestBody SaveCountryResource resource){
        Country country = convertToEntity(resource);
        return convertToResource(countryService.createCountry(country));
    }

    @PutMapping("/countries/{countryId}")
    @Operation(summary = "update a country", tags = {"countries"})
    public CountryResource updateCountry(@PathVariable Long countryId,@RequestBody SaveCountryResource resource){
        Country country = convertToEntity(resource);
        return convertToResource(countryService.updateCountry(countryId, country));
    }

    @DeleteMapping("/countries/{countryId}")
    @Operation(summary = "delete a country", tags = {"countries"})
    public ResponseEntity<?> deleteCountry(@PathVariable Long countryId)
    {
        return countryService.deleteCountry(countryId);
    }

    @GetMapping("/countries/{countryId}")
    @Operation(summary = "Get a country by id", tags = {"countries"})
    public CountryResource getCountryById(@PathVariable Long countryId){
        return convertToResource(countryService.getCountryById(countryId));
    }
}
