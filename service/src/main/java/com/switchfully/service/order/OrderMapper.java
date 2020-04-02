package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.order.Order;
import com.switchfully.service.item.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Component
public class OrderMapper {
    private ItemMapper itemMapper;

    @Autowired
    public OrderMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public Order toNewOrder(CreateOrderDto createOrderDto) {
        Map<Item, Integer> orders = new ConcurrentHashMap<>();
        createOrderDto.getItems()
                .forEach(item -> {
                    Item key = itemMapper.toNewItem(item);
                    orders.put(key, orders.get(key) == null ? 1 : orders.get(key) + 1);
                });
        return new Order(orders);
    }

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getItems().entrySet().stream()
                        .collect(Collectors.toConcurrentMap(entry -> itemMapper.toItemDto(entry.getKey()), Map.Entry::getValue)),
                order.getShippingDate(),
                order.getTotalAmount());
    }
}
