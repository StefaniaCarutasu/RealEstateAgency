package com.example.real_estate_website.dtos;

import com.example.real_estate_website.models.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ListingDto {
    private Property property;
    private AppUser agent;
    private Integer askingPrice;
    private Integer soldPrice;
    private String currency;
    private Boolean isAvailable;
    private List<Bid> bids;
    private Date startDate;
    private Date endDate;
    private Double rating;


    @Override
    public String toString() {
        return "ListingDto{" +
            "property=" + property +
            ", agent=" + agent +
            ", askingPrice=" + askingPrice +
            ", soldPrice=" + soldPrice +
            ", currency=" + currency +
            ", isAvailable=" + isAvailable +
            ", bids=" + bids +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", rating=" + rating +
            '}';
    }
}
