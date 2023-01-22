package com.example.real_estate_website.services.implementations;

import com.example.real_estate_website.daos.ListingDao;
import com.example.real_estate_website.dtos.BidDto;
import com.example.real_estate_website.dtos.ListingDto;
import com.example.real_estate_website.exceptions.ListingNotFoundException;
import com.example.real_estate_website.exceptions.PropertyNotFoundException;
import com.example.real_estate_website.exceptions.UserNotFoundException;
import com.example.real_estate_website.mapper.BidMapper;
import com.example.real_estate_website.mapper.ListingMapper;
import com.example.real_estate_website.models.*;
import com.example.real_estate_website.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("listingsService")
public class ListingsServiceImpl implements ListingsService {

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private ListingMapper listingMapper;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private BidMapper bidMapper;

    @Autowired
    private BidService bidService;

    @Override
    public List<ListingDto> getAllListings() {
        List<Listing> listings = listingDao.findAll();
        return listings.stream().map(listing -> listingMapper.mapToListingDto(listing)).toList();
    }

    @Override
    public Listing getListingById(Long listingId) {
        Listing listing = listingDao.getListingById(listingId);
        if (listing != null) {
            return listing;
        } else {
            throw new ListingNotFoundException("Listing not found");
        }
    }

    @Override
    public boolean addListing(Long propertyId, Long agentId, ListingDto listingDto) {
        Optional<Property> property = propertiesService.getPropertyById(propertyId);
        AppUser agent = usersService.getUserById(agentId);
        if (property.isPresent()) {
            listingDto.setProperty(property.get());
            listingDto.setAgent(agent);
            Listing listing = listingMapper.mapToListing(listingDto);
            try {
                listingDao.save(listing);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new PropertyNotFoundException("Property with id " + propertyId + " not found");
        }
    }

    @Override
    public boolean modifyListing(Listing listing) {
        try {
            listingDao.save(listing);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteListing(Long listingId) {
        try {
            listingDao.deleteById(listingId);
            return true;
        } catch (Exception e) {
            throw new ListingNotFoundException("Listing not found");
        }
    }

    @Override
    public boolean addReviewToListing(Long listingId, Long userId, String reviewContent) {
        Listing listing = getListingById(listingId);
        if (listing == null) {
            throw  new ListingNotFoundException("Listing with id " + listingId + " not found");
        }

        AppUser user = usersService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        List<Review> reviews = listing.getReviews();
        Review review = Review.builder().listing(listing).appUser(user).content(reviewContent).datePosted(new Date()).build();

        reviews.add(review);
        try {
            modifyListing(listing);
            reviewService.addReview(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addBidToListing(Long listingId, Long userId, BidDto bidDto) {
        Listing listing = getListingById(listingId);
        AppUser bidder = usersService.getUserById(userId);
        Bid bid = bidMapper.mapToBid(bidDto);
        try {
            bid.setListing(listing);
            bid.setBidder(bidder);
            List<Bid> bids = listing.getBids();
            bids.add(bid);
            listing.setBids(bids);
            bidService.addBid(bid);
            modifyListing(listing);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
