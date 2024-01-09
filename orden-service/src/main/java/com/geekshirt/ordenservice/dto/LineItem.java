package com.geekshirt.ordenservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LineItem {
    private String upc;
    private int quantity;
    private double price;
}
