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
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

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
    Order order = new Order(item, 2, LocalDate.now());
    OrderRequestDto orderRequestDto = new OrderRequestDto("id", 2);

    @Mock
    ItemMapper itemMapper;

    @InjectMocks
    OrderMapper orderMapper = new OrderMapper(itemMapper);

    @Test
    void toNewOrder_returnsOrder() {
        Order order = orderMapper.toNewOrder(item, 2, LocalDate.now().plusDays(7));
        assertThat(order.getItem()).isEqualTo(item);
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