package com.example.real_estate_website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@Entity
@Table(name = "LISTING")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="PROPERTY_ID")
    @JsonIgnore
    private Property property;

    @ManyToOne
    @JoinColumn(name="AGENT_ID")
    @JsonIgnore
    private AppUser agent;

    @Column(name = "asking_price")
    private Integer askingPrice;

    @Column(name = "sold_price")
    private Integer soldPrice;

    private String currency;

    @Column(name = "is_available")
    @JsonProperty
    private Boolean isAvailable;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<Bid> bids;

    @Column(name = "start_date", nullable = true)
    private Date startDate;

    @Column(name = "end_date", nullable = true)
    private Date endDate;

    @Column(name = "rating", nullable = true)
    private Double rating;

    @OneToMany(mappedBy = "listing")
    private List<Review> reviews;

}
