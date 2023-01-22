package com.example.real_estate_website.service;

import com.example.real_estate_website.daos.ListingDao;
import com.example.real_estate_website.daos.PropertyDao;
import com.example.real_estate_website.daos.UserDao;
import com.example.real_estate_website.dtos.BidDto;
import com.example.real_estate_website.dtos.ListingDto;
import com.example.real_estate_website.mapper.BidMapper;
import com.example.real_estate_website.mapper.ListingMapper;
import com.example.real_estate_website.mocks.BidMock;
import com.example.real_estate_website.mocks.ListingMock;
import com.example.real_estate_website.mocks.PropertyMock;
import com.example.real_estate_website.mocks.UserMock;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Bid;
import com.example.real_estate_website.models.Listing;
import com.example.real_estate_website.models.Property;
import com.example.real_estate_website.services.implementations.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListingServiceTest {

    @Mock
    ListingDao listingDao;

    @Mock
    PropertyDao propertyDao;

    @Mock
    UserDao userDao;

    @Mock
    private ListingMapper listingMapper;

    @Mock
    private BidMapper bidMapper;

    @Mock
    private UsersServiceImpl usersService;

    @Mock
    private BidServiceImpl bidService;

    @Mock
    private PropertiesServiceImpl propertiesService;

    @InjectMocks
    private ListingsServiceImpl listingsService;

    @Test
    void testGetAllListings() {
        Listing listing = ListingMock.mockListing();
        ListingDto listingDto = ListingMock.mockListingDto();

        when(listingDao.findAll()).thenReturn(List.of(listing));
        when(listingMapper.mapToListingDto(listing)).thenReturn(listingDto);

        List<ListingDto> result = listingsService.getAllListings();

        assertEquals(result, List.of(listingDto));
    }

    @Test
    void testGetListingById() {
        Listing listing = ListingMock.mockListing();

        when(listingDao.getListingById(6L)).thenReturn(listing);

        Listing result = listingsService.getListingById(6L);

        assertEquals(result, listing);
    }

    @Test
    void testAddListing() {
        Property property = PropertyMock.mockProperty();
        AppUser agent = UserMock.mockUser();
        Listing listing = ListingMock.mockListing();
        ListingDto listingDto = ListingMock.mockListingDto();

        when(propertiesService.getPropertyById(2L)).thenReturn(Optional.ofNullable(property));
        when(usersService.getUserById(3L)).thenReturn(agent);
        when(listingMapper.mapToListing(listingDto)).thenReturn(listing);
        when(listingDao.save(listing)).thenReturn(listing);

        boolean result = listingsService.addListing(2L, 3L, listingDto);

        assertTrue(result);
    }

    @Test
    void testAddBidToListing() {
        AppUser bidder = UserMock.mockUser();
        Listing listing = ListingMock.mockListing();
        Bid bid = BidMock.mockBid();
        BidDto bidDto = BidMock.mockBidDto();
        List<Bid> bids = listing.getBids();
        if (bids == null) {
            bids = new ArrayList<>();
        }

        when(listingDao.getListingById(6L)).thenReturn(listing);
        when(listingsService.getListingById(6L)).thenReturn(listing);

        when(usersService.getUserById(3L)).thenReturn(bidder);
        when(bidMapper.mapToBid(bidDto)).thenReturn(bid);
        when(bidService.addBid(bid)).thenReturn(true);

        bids.add(bid);
        listing.setBids(bids);
        when(listingDao.save(listing)).thenReturn(listing);
        when(listingsService.modifyListing(listing)).thenReturn(true);

        boolean result = listingsService.addBidToListing(6L, 3L, bidDto);

        assertTrue(result);
    }


}
