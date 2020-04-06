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
    private ItemRepository itemRepository;

    @Autowired
    public OrderMapper(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public Order toNewOrder(String itemId, int amount) {
        Map<Item, Integer> orders = new ConcurrentHashMap<>();
        Item item = getCorrectItemAndDecrementAmountInDatabase(itemId, amount);
        LocalDate shippingDate = setCorrectShippingDate(itemId);
        orders.put(item, amount);
        return new Order(orders, shippingDate);
    }

    private Item getCorrectItemAndDecrementAmountInDatabase(String itemId, int amount) {
        Item item = itemRepository.getItemById(itemId);
        itemRepository.decrementItemAmount(item, amount);
        return item;
    }

    private LocalDate setCorrectShippingDate(String itemId) {
        if (itemRepository.getAmountOfItems(itemId) <= 0) {
            return LocalDate.now().plusDays(7);
        }
        return LocalDate.now().plusDays(1);
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
