package com.example.real_estate_website.controllers;

import com.example.real_estate_website.dtos.BidDto;
import com.example.real_estate_website.dtos.ListingDto;
import com.example.real_estate_website.exceptions.ListingNotFoundException;
import com.example.real_estate_website.exceptions.UserNotFoundException;
import com.example.real_estate_website.mapper.ListingMapper;
import com.example.real_estate_website.models.Bid;
import com.example.real_estate_website.models.Listing;
import com.example.real_estate_website.services.ListingsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/listings")
public class ListingsController extends BaseController{

    @Autowired
    private ListingsService listingsService;

    @Autowired
    private ListingMapper listingMapper;


    @GetMapping(value = "")
    @ResponseBody
    @ApiOperation(
        value = "Retrieves all listings",
        response = ListingDto.class, responseContainer = "List"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> getAllListings() {
        try {
            List<ListingDto> listingsList = listingsService.getAllListings();
            logAction("Retrieve all listings", "ListingsController");
            return new ResponseEntity<>(listingsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{listingId}")
    @ResponseBody
    @ApiOperation(
        value = "Retrieves a listing by it's id",
        response = ListingDto.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> getListingById(@PathVariable Long listingId) {
        try {
            Listing listing = listingsService.getListingById(listingId);
            ListingDto listingDto = listingMapper.mapToListingDto(listing);
            logAction("Retrieve listing with id " + listingId, "ListingsController");
            return new ResponseEntity<>(listingDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addListing")
    @ResponseBody
    @ApiOperation(
        value = "Adds a new listing",
        response = ResponseEntity.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> addNewListing(@RequestParam Long propertyId, @RequestParam Long agentId,
                                                @RequestBody ListingDto listingDto) {
        try {
            boolean isSuccess = listingsService.addListing(propertyId, agentId, listingDto);
            if (Boolean.TRUE.equals(isSuccess)) {
                logAction("Add new listing" , "ListingsController");
                return new ResponseEntity<>("Listing added successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Could not add listing", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addBid", method = RequestMethod.PATCH)
    @ResponseBody
    @ApiOperation(
        value = "Retrieves list of bids for listing"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class)
    })
    public ResponseEntity<Object> addBidToListing(@RequestParam Long listingId, @RequestParam Long userId, @RequestBody BidDto newBid) {

        try {
            boolean idSuccess = listingsService.addBidToListing(listingId, userId, newBid);
            if (Boolean.TRUE.equals(idSuccess)) {
                logAction("Add bid to listing with id " + listingId, "ListingsController");
                return new ResponseEntity<>("Bid added successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Could not find listing", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/deleteListing/{listingId}")
    @ResponseBody
    @ApiOperation(
        value = "Deletes a listing"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class)
    })
    public ResponseEntity<Object> deleteListing(@PathVariable Long listingId) {
        try {
            listingsService.deleteListing(listingId);

            logAction("Deleted listing with id " + listingId, "ListingsController");
            return new ResponseEntity<>("Listing deleted successfully", HttpStatus.OK);
        } catch (ListingNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/addReview/{listingId}/{userId}")
    @ResponseBody
    @ApiOperation(
        value = "Adds a review to a listing"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class)
    })
    public ResponseEntity<Object> addReview(@PathVariable Long listingId, @PathVariable Long userId,
                                            @RequestBody String content) {
        try {
            boolean isSuccess = listingsService.addReviewToListing(listingId, userId, content);

            if (Boolean.TRUE.equals(isSuccess)) {
                return new ResponseEntity<>("Listing deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ListingNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
