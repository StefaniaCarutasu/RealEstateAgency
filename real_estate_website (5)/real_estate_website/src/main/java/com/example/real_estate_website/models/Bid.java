package com.example.real_estate_website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name = "BID")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="listing_id", nullable=false)
    @JsonIgnore
    private Listing listing;

    @ManyToOne
    @JoinColumn(name="bidder_id", nullable=false)
    @JsonIgnore
    private AppUser bidder;

    private Integer offer;
}
