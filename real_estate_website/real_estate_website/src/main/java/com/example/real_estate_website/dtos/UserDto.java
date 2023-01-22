package com.example.real_estate_website.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String age;
    private String email;
    private String phoneNumber;
    private String role;
}
