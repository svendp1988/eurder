package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.order.Order;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.dto.ItemDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
            .withAmount(2)
            .build();
    ItemDto itemDto = new ItemDto(1L, "name", "description", 2.5, null, 2);
    Order order = new Order(Map.of(item, 2));
    OrderRequestDto orderRequestDto = new OrderRequestDto(Map.of("id", 2));

    @Mock
    ItemMapper itemMapper;

    @InjectMocks
    OrderMapper orderMapper = new OrderMapper(itemMapper);

    @Test
    void toNewOrder_returnsOrder() {
        Order order = orderMapper.toNewOrder(Map.of(item, 2));
        assertThat(order.getOrders().keySet()).contains(item);
        assertThat(order.getTotalAmount()).isEqualTo(5);
    }

    @Test
    void toDto_returnsOrderDto() {
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        OrderDto actualOrderDto = orderMapper.toDto(order);
        assertEquals(order.getOrderId(), actualOrderDto.getId());
        assertEquals(order.getTotalAmount(), actualOrderDto.getTotalAmount());

    }
}