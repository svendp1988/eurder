package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.Order;
import com.switchfully.domain.order.OrderRepository;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private ItemRepository itemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, UserMapper userMapper, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.itemRepository = itemRepository;
    }

    public OrderDto addOrder(Authentication authentication, OrderRequestDto orderRequestDto) {
        String userId = getUserId(authentication);
        Item item = itemRepository.getItemById(orderRequestDto.getItemId());
        int amount = orderRequestDto.getAmount();
        LocalDate shippingDate = setCorrectShippingDateAndDecrementAmountInDatabase(item, amount);
        return orderMapper.toDto(orderRepository.addOrder(userId, orderMapper.toNewOrder(item, amount, shippingDate)));
    }

    public List<OrderDto> getReportOfOrders(Authentication authentication) {
        String userId = getUserId(authentication);
        return orderRepository.getReportOfOrders(userId).stream()
                .map(order -> orderMapper.toDto(order))
                .collect(Collectors.toList());
    }

    public OrderDto reOrder(Authentication authentication, String orderId) {
        String userId = getUserId(authentication);
        Order foundOrder = orderRepository.getReportOfOrders(userId).stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow();
        Item item = foundOrder.getItem();
        int amount = foundOrder.getAmount();
        LocalDate shippingDate = setCorrectShippingDateAndDecrementAmountInDatabase(item, amount);
        return orderMapper.toDto(orderRepository.addOrder(userId, orderMapper.toNewOrder(item, amount, shippingDate)));
    }

    String getUserId(Authentication authentication) {
        return userMapper.toUserDto(userRepository.getAllCustomers().stream()
                .filter(user -> user.getEmail().equals(authentication.getName()))
                .findFirst()
                .orElseThrow()).getId();
    }

    LocalDate setCorrectShippingDateAndDecrementAmountInDatabase(Item item, int amount) {
        LocalDate shippingDate = LocalDate.now().plusDays(1);
        if (itemRepository.getAmountOfItems(item) - amount < 0) {
            shippingDate = LocalDate.now().plusDays(7);
        }
        itemRepository.decrementItemAmount(item, amount);
        return shippingDate;
    }
}
