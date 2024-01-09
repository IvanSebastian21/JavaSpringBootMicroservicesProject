package com.geekshirt.ordenservice.utils;

import com.geekshirt.ordenservice.dto.OrderResponseDto;
import com.geekshirt.ordenservice.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public OrderResponseDto convertEntityToDto(Order order){
        return modelMapper.map(order, OrderResponseDto.class);
    }

    public List<OrderResponseDto> convertEntityToDto(List<Order> orderList){

        return orderList.stream()
                .map(order -> convertEntityToDto(order))
                .collect(Collectors.toList());
    }
}
