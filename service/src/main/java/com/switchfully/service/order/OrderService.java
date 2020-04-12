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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<Item, Integer> orders = new HashMap<>();
        for (Map.Entry<String, Integer> entry : orderRequestDto.getOrder().entrySet()) {
            Item item = itemRepository.getItemById(entry.getKey());
            int amount = entry.getValue();
            setCorrectShippingDateAndDecrementAmountInDatabase(item, amount);
            orders.put(item, amount);
        }
        return orderMapper.toDto(orderRepository.addOrder(userId, orderMapper.toNewOrder(orders)));
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
        Map<Item, Integer> orders = new HashMap<>();
        for (Map.Entry<Item, Integer> entry : foundOrder.getOrders().entrySet()) {
            Item item = entry.getKey();
            int amount = entry.getValue();
            setCorrectShippingDateAndDecrementAmountInDatabase(item, amount);
            orders.put(item, amount);
        }
        return orderMapper.toDto(orderRepository.addOrder(userId, orderMapper.toNewOrder(orders)));
    }

    String getUserId(Authentication authentication) {
        return userMapper.toUserDto(userRepository.getAllCustomers().stream()
                .filter(user -> user.getEmail().equals(authentication.getName()))
                .findFirst()
                .orElseThrow()).getId();
    }

    void setCorrectShippingDateAndDecrementAmountInDatabase(Item item, int amount) {
        LocalDate shippingDate = LocalDate.now().plusDays(1);
        if (itemRepository.getAmountOfItems(item) - amount < 0) {
            shippingDate = LocalDate.now().plusDays(7);
        }
        itemRepository.decrementItemAmount(item, amount);
        item.setShippingDate(shippingDate);
    }
}
