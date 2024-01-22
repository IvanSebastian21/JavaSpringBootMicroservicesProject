package com.geekshirt.ordenservice.utils;

public enum ExceptionMessagesEnum {
    ACCOUNT_NOT_FOUND("Account not found."),
    INCLUSION_NOT_EMPTY("The inclusion of an empty item is not permitted in the order."),
    INCORRECT_REQUEST_EMPTY_ITEMS_ORDER("Empty item Not Allowed in order."),
    ORDER_NOT_FOUND("Order not found."),
    ORDERS_WERE_NOT_FOUND("Orders were not found.");
    ExceptionMessagesEnum(String msg){
        value = msg;
    }
    private final String value;
    public String getValue(){
        return value;
    }
}
