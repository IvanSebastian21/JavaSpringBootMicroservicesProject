package com.geekshirt.ordenservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Data
public class OrderRequestDto {
    private String accountId;
    private List<LineItem> items;
}
