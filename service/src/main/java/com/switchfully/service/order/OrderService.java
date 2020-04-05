package com.switchfully.service.order;

import com.switchfully.domain.order.OrderRepository;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, UserMapper userMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public OrderDto addOrder(Authentication authentication, String itemId, int amount) {
        String userId = getUserId(authentication);
        return orderMapper.toDto(orderRepository.addOrder(userId, orderMapper.toNewOrder(itemId, amount)));
    }

    public String getUserId(Authentication authentication) {
        return userMapper.toUserDto(userRepository.getAllCustomers().stream()
                .filter(user -> user.getEmail().equals(authentication.getName()))
                .findFirst()
                .orElseThrow()).getId();
    }

    public List<OrderDto> getReportOfOrders(Authentication authentication) {
        String userId = getUserId(authentication);
        return orderRepository.getReportOfOrders(userId).stream()
                .map(order -> orderMapper.toDto(order))
                .collect(Collectors.toList());
    }
}
