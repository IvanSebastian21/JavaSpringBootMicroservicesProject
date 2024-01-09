package com.geekshirt.ordenservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
