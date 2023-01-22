package com.example.real_estate_website.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestError {
    private Integer errorCode;
    private String customMessage;
    private String errorMessage;
}
