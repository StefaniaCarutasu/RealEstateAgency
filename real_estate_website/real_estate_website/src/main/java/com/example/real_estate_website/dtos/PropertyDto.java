package com.example.real_estate_website.dtos;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class PropertyDto {
    private String address;
    private Integer numberOfRooms;
    private Integer numberOfBathrooms;
    private Double surface;
    private Double yardSurface;
}
