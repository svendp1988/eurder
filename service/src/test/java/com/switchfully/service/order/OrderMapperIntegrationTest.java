package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.Order;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.dto.ItemDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderMapperIntegrationTest {
    Item item = itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(2.5)
            .build();
    ItemDto itemDto = new ItemDto("id", "name", "description", 2.5);
    Map<Item, Integer> items = Map.of(itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(2.5)
            .build(), 2);
    Order order = new Order(items, LocalDate.now());

    @Mock
    ItemMapper itemMapper;
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    OrderMapper orderMapper = new OrderMapper(itemMapper, itemRepository);

    @Test
    void toNewOrder_returnsOrder() {
        when(itemRepository.getItemById("id")).thenReturn(item);
        Order order = orderMapper.toNewOrder("id", 2);
        assertThat(order.getItems()).isEqualTo(items);
        assertThat(order.getTotalAmount()).isEqualTo(5);
        assertThat(order.getShippingDate()).isEqualTo(LocalDate.now().plusDays(7));
    }

    @Test
    void toDto_returnsOrderDto() {
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        OrderDto actualOrderDto = orderMapper.toDto(order);
        assertEquals(order.getOrderId(), actualOrderDto.getId());
        assertEquals(order.getShippingDate(), actualOrderDto.getShippingDate());
        assertEquals(order.getTotalAmount(), actualOrderDto.getTotalAmount());

    }
}