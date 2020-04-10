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

    public Order toNewOrder(OrderRequestDto orderRequestDto) {
        String itemId = orderRequestDto.getItemId();
        int amount = orderRequestDto.getAmount();
        Item item = getCorrectItemAndDecrementAmountInDatabase(itemId, amount);
        LocalDate shippingDate = setCorrectShippingDate(itemId);
        return new Order(item, amount, shippingDate);
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
                itemMapper.toItemDto(order.getItem()),
                order.getAmount(),
                order.getShippingDate(),
                order.getTotalAmount());
    }
}
