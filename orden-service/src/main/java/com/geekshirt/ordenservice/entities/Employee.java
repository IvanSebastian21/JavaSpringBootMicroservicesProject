package com.geekshirt.ordenservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Employee {
        private String name;
        private String gender;
        private Address homeAddress;
}
