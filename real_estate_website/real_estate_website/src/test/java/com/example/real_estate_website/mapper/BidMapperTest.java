package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.BidDto;
import com.example.real_estate_website.mocks.BidMock;
import com.example.real_estate_website.models.Bid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BidMapperTest {

    @InjectMocks
    BidMapper bidMapper;

    @Test
    void mapToBidTest(){
        //GIVEN
        BidDto bidDto = BidMock.mockBidDto();

        //WHEN
        Bid bid = bidMapper.mapToBid(bidDto);

        //THEN
        assertEquals(bid.getOffer(), bidDto.getOffer());
    }

    @Test
    void mapToListingDtoTest(){
        //GIVEN
        Bid bid = BidMock.mockBid();

        //WHEN
        BidDto bidDto = bidMapper.mapToBidDto(bid);

        //THEN
        assertEquals(bid.getOffer(), bidDto.getOffer());
    }
}
