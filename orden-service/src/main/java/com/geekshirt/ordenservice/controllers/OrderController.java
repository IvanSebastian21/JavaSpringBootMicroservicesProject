package com.geekshirt.ordenservice.controllers;

import com.geekshirt.ordenservice.dto.OrderRequestDto;
import com.geekshirt.ordenservice.dto.OrderResponseDto;
import com.geekshirt.ordenservice.entities.Order;
import com.geekshirt.ordenservice.services.OrderService;
import com.geekshirt.ordenservice.utils.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityDtoConverter converter;

    @GetMapping("/")
    public String saludar() {
        return "Hola Mundo";
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        List<Order> orderList = orderService.findAllOrders();
        return new ResponseEntity<>(converter.convertEntityToDto(orderList), HttpStatus.OK);
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable String orderId) {
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(converter.convertEntityToDto(order), HttpStatus.OK);
    }

    @GetMapping("order/generate/{orderId}")
    public ResponseEntity<OrderResponseDto> findByGeneratedId(@PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        return new ResponseEntity<>(converter.convertEntityToDto(order), HttpStatus.OK);
    }


    @GetMapping("order/byAccount/{accountId}")
    public ResponseEntity<List<OrderResponseDto>> findOrdersByAccountId(@PathVariable String accountId) {
        List<Order> orders = orderService.findOrdersByAccountId(accountId);
        return new ResponseEntity<>(converter.convertEntityToDto(orders), HttpStatus.OK);
    }

    @PostMapping("order/create")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto payload) {
        Order order = orderService.createOrder(payload);
        return new ResponseEntity<>(converter.convertEntityToDto(order), HttpStatus.CREATED);
    }
}
