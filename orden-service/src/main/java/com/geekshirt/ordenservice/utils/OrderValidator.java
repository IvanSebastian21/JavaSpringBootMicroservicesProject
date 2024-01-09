package com.geekshirt.ordenservice.utils;

import com.geekshirt.ordenservice.dto.OrderRequestDto;
import com.geekshirt.ordenservice.exceptions.IncorrectOrderRequestException;

public class OrderValidator {
    public static boolean validateOrder(OrderRequestDto orderRequestDto) {
        if (orderRequestDto.getItems() == null || orderRequestDto.getItems().isEmpty()) {
            throw new IncorrectOrderRequestException(ExceptionMessagesEnum.INCORRECT_REQUEST_EMPTY_ITEMS_ORDER.getValue());
        }
        return true;
    }
}
