package com.example.real_estate_website.controllers;

import com.example.real_estate_website.dtos.PropertyDto;
import com.example.real_estate_website.exceptions.UserNotFoundException;
import com.example.real_estate_website.exceptions.UserNotInRoleException;
import com.example.real_estate_website.mapper.PropertyMapper;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Property;
import com.example.real_estate_website.services.PropertiesService;
import com.example.real_estate_website.services.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertiesController extends BaseController{

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PropertyMapper propertyMapper;

    @PostMapping(value = "/addProperty")
    @ResponseBody
    @ApiOperation(
        value = "Adds new property"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Internal server error", response = ResponseEntity.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class)
    })
    public ResponseEntity<Object> addProperty(@RequestParam Long userId, @RequestBody PropertyDto propertyDto) {
        try {
            AppUser user = usersService.getUserById(userId);
            boolean isSuccess = propertiesService.addProperty(propertyDto, user);
            if (Boolean.TRUE.equals(isSuccess)) {
                return new ResponseEntity<>("Property added successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Property could not be added.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getProperties")
    @ResponseBody
    @ApiOperation(value = "Gets all properties for owner")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Internal server error", response = ResponseEntity.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class)
    })
    public ResponseEntity<Object> getAllPropertiesByOwner(@RequestParam Long ownerId) {
        try {
            List<Property> propertyList = propertiesService.getAllPropertiesForOwner(ownerId);
            return new ResponseEntity<>(propertyList, HttpStatus.OK);
        } catch (UserNotInRoleException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
