package com.example.real_estate_website.models;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "JP_AUDIT")
public class JP_AUDIT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    @Column(name = "origin_point")
    private String originPoint;

//    @Column(name = "date")
//    private Timestamp date;
}
