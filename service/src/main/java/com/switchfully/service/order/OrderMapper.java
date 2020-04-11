package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.Order;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

    public Order toNewOrder(Item item, int amount, LocalDate shippingDate) {
        return new Order(item, amount, shippingDate);
    }

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                itemMapper.toItemDto(order.getItem()),
                order.getAmount(),
                order.getShippingDate(),
                order.getTotalAmount());
    }
}
