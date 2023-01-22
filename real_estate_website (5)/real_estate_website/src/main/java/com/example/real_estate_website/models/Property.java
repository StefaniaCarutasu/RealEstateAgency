package com.example.real_estate_website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name = "PROPERTY")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

    @Column(name = "number_of_bathrooms")
    private Integer numberOfBathrooms;

    private Double surface;

    @Column(name = "yard_surface")
    private Double yardSurface;

    @ManyToOne
    @JoinColumn(name="OWNER_ID", nullable=false)
    @JsonIgnore
    private AppUser owner;
}
