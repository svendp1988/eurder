package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.Order;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private ItemMapper itemMapper;

    @Autowired
    public OrderMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public Order toNewOrder(Map<Item, Integer> orders) {
        return new Order(orders);
    }

    public OrderDto toDto(Order order) {
        Map<ItemDto, Integer> mappedOrders = new HashMap<>();
        order.getOrders().forEach((key, value) -> {
            ItemDto itemDto = itemMapper.toItemDto(key);
            mappedOrders.put(itemDto, value);
        });
        return new OrderDto(
                order.getOrderId(),
                mappedOrders,
                order.getTotalAmount());
    }
}
