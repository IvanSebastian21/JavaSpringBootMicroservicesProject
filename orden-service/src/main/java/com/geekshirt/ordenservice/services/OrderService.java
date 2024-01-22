package com.geekshirt.ordenservice.services;

import com.geekshirt.ordenservice.config.client.CustomerServiceClient;
import com.geekshirt.ordenservice.dao.JpaOrderDAO;
import com.geekshirt.ordenservice.dto.AccountDto;
import com.geekshirt.ordenservice.dto.OrderRequestDto;
import com.geekshirt.ordenservice.entities.Order;
import com.geekshirt.ordenservice.entities.OrderDetail;
import com.geekshirt.ordenservice.exceptions.AccountNotFoundException;
import com.geekshirt.ordenservice.exceptions.OrderNotFoundException;
import com.geekshirt.ordenservice.repository.OrderRepository;
import com.geekshirt.ordenservice.utils.Constants;
import com.geekshirt.ordenservice.utils.ExceptionMessagesEnum;
import com.geekshirt.ordenservice.utils.OrderStatusEnum;
import com.geekshirt.ordenservice.utils.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private JpaOrderDAO jpaOrderDAO;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Order createOrder(OrderRequestDto orderRequestDTO) {
        OrderValidator.validateOrder(orderRequestDTO);
        AccountDto accountDto = customerServiceClient
                .findAccountById(orderRequestDTO.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(ExceptionMessagesEnum.ACCOUNT_NOT_FOUND.getValue()));
        Order order = initOrder(orderRequestDTO);
        return orderRepository.save(order);
    }

    private Order initOrder(OrderRequestDto orderRequest) {
        Order orderObj = new Order();
        orderObj.setOrderId(UUID.randomUUID().toString());
        orderObj.setAccountId(orderRequest.getAccountId());
        orderObj.setStatus(OrderStatusEnum.PENDING);

        List<OrderDetail> orderDetails = orderRequest.getItems().stream()
                .map(item -> OrderDetail.builder()
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .upc(item.getUpc())
                        .tax((item.getPrice() * item.getQuantity()) * Constants.TAX_IMPORT)
                        .order(orderObj)
                        .build())
                .collect(Collectors.toList());

        orderObj.setDetailList(orderDetails);
        orderObj.setTotalAmount(orderDetails.stream().mapToDouble(OrderDetail::getPrice).sum());
        orderObj.setTotalTax(orderObj.getTotalAmount() * Constants.TAX_IMPORT);
        orderObj.setTransactionDate(new Date());

        return orderObj;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(String orderId) {
        Optional<Order> order = Optional.ofNullable(orderRepository.findOrderByOrderId(orderId));
        return order.orElseThrow(() -> new OrderNotFoundException(ExceptionMessagesEnum.ORDER_NOT_FOUND.getValue()));
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(ExceptionMessagesEnum.ORDER_NOT_FOUND.getValue()));
    }

    // Devuelve una lista de ordenes según la cuenta
    public List<Order> findOrdersByAccountId(String accountId){
        Optional<List<Order>> orders = Optional.ofNullable(orderRepository.findOrdersByAccountId(accountId));
        return orders
                .orElseThrow(() -> new OrderNotFoundException(ExceptionMessagesEnum.ORDERS_WERE_NOT_FOUND.getValue()));
    }
}
