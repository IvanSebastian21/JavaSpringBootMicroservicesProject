package com.geekshirt.ordenservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmployeeDto {
    private String name;
    private String gender;
    private String homeCity;
    private int homePin;
}
