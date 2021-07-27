package com.frikiteam.review.controller;

import com.frikiteam.review.domain.model.District;
import com.frikiteam.review.domain.service.DistrictService;
import com.frikiteam.review.resource.DistrictResource;
import com.frikiteam.review.resource.SaveDistrictResource;
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
@RequestMapping("/api/cities/{cityId}/districts")
public class DistrictsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private DistrictService districtService;

    private District convertToEntity(SaveDistrictResource resource){
        return mapper.map(resource,District.class);
    }
    private DistrictResource convertToResource(District entity){
        return mapper.map(entity,DistrictResource.class);
    }

    @GetMapping
    @Operation(summary = "get all districts by pages", tags = {"city-districts"})
    public Page<DistrictResource> getAllDistricts(Pageable pageable){
        List<DistrictResource> resources = districtService.getAllDistricts(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping
    @Operation(summary = "create a district by a city id", tags = {"city-districts"})
    public DistrictResource createDistrict(@PathVariable Long cityId, @Valid @RequestBody SaveDistrictResource resource){
        District district = convertToEntity(resource);
        return convertToResource(districtService.createDistrict(cityId, district));
    }

    @PutMapping("{districtId}")
    @Operation(summary = "update a district", tags = {"city-districts"})
    public DistrictResource updateDistrict(@PathVariable Long districtId,@RequestBody SaveDistrictResource resource){
        District district = convertToEntity(resource);
        return convertToResource(districtService.updateDistrict(districtId,district));
    }

    @DeleteMapping("{districtId}")
    @Operation(summary = "delete a district", tags = {"city-districts"})
    public ResponseEntity<?> deleteDistrict(@PathVariable Long districtId)
    {
        return districtService.deleteDistrict(districtId);
    }

    @GetMapping("{districtId}")
    @Operation(summary = "Get a district by id", tags = {"city-districts"})
    public DistrictResource getDistrictById(@PathVariable Long districtId){
        return convertToResource(districtService.getDistrictById(districtId));
    }
}
