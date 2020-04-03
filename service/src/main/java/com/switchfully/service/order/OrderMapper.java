package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.order.Order;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private ItemMapper itemMapper;
    private ItemService itemService;

    @Autowired
    public OrderMapper(ItemMapper itemMapper, ItemService itemService) {
        this.itemMapper = itemMapper;
        this.itemService = itemService;
    }

    public Order toNewOrder(List<String> items) {
//        Map<Item, Integer> orders = new ConcurrentHashMap<>();
//        orders = items.stream()
//                .map(item -> itemService.getItemByName(item))
////                .collect(Collectors.groupingBy(Function.identity(), item -> orders.get(item) == null ? 1 : orders.get(item) + 1));
        return null; //new Order(orders);

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
