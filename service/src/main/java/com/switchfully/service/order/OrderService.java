package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.Order;
import com.switchfully.domain.order.OrderRepository;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.address.AddressDto;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.user.UserMapper;
import com.switchfully.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, UserMapper userMapper, ItemRepository itemRepository, ItemMapper itemMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public OrderDto addOrder(Authentication authentication, OrderRequestDto orderRequestDto) {
        String userId = getUserId(authentication);
        Map<Item, Integer> orders = new HashMap<>();
        orderRequestDto.getOrder().forEach((key, value) -> {
            Item item = itemRepository.getItemById(key);
            Item soldItem = new Item(item);
            int amount = value;
            setCorrectShippingDateAndDecrementAmountInDatabase(item, soldItem, amount);
            orders.put(soldItem, amount);
        });
        return orderMapper.toDto(orderRepository.addOrder(userId, orderMapper.toNewOrder(orders)));
    }

    public List<OrderDto> getReportOfOrders(Authentication authentication) {
        String userId = getUserId(authentication);
        return orderRepository.getReportOfOrders(userId).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderDto reOrder(Authentication authentication, String orderId) {
        String userId = getUserId(authentication);
        Order foundOrder = getCorrectOrder(orderId, userId);
        Map<Item, Integer> orders = new HashMap<>();
        foundOrder.getOrders().forEach((item, value) -> {
            Item soldItem = new Item(item);
            int amount = value;
            setCorrectShippingDateAndDecrementAmountInDatabase(item, soldItem, amount);
            orders.put(soldItem, amount);
        });
        return orderMapper.toDto(orderRepository.addOrder(userId, orderMapper.toNewOrder(orders)));
    }

    private Order getCorrectOrder(String orderId, String userId) {
        return orderRepository.getReportOfOrders(userId).stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow();
    }

    String getUserId(Authentication authentication) {
        return userMapper.toUserDto(userRepository.getAllCustomers().stream()
                .filter(user -> user.getEmail().equals(authentication.getName()))
                .findFirst()
                .orElseThrow()).getId();
    }

    void setCorrectShippingDateAndDecrementAmountInDatabase(Item item, Item soldItem, int amount) {
        LocalDate shippingDate = LocalDate.now().plusDays(1);
        if (itemRepository.getAmountOfItems(item) - amount < 0) {
            shippingDate = LocalDate.now().plusDays(7);
        }
        itemRepository.decrementItemAmount(item, amount);
        soldItem.setShippingDate(shippingDate);
    }

    public List<Report> viewItemsShippingToday() {
        List<Report> reports = new ArrayList<>();
        orderRepository.getOrdersPerUser().forEach((userId, orders) -> {
            orders.stream().map(Order::getOrders).flatMap(items -> items.entrySet().stream()).filter(entrySet -> entrySet.getKey().getShippingDate().equals(LocalDate.now())).forEach(entrySet -> {
                ItemDto item = itemMapper.toItemDto(entrySet.getKey());
                int amount = entrySet.getValue();
                UserDto user = userMapper.toUserDto(userRepository.getById(userId));
                AddressDto address = user.getAddressDto();
                reports.add(new Report(item, amount, user, address));
            });
        });
        return reports;
    }
}
