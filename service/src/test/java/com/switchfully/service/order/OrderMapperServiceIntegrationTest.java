package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.Order;
import com.switchfully.domain.order.OrderRepository;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.domain.user.feature.Feature;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.user.UserMapper;
import com.switchfully.service.user.role.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static com.switchfully.service.testbuilders.TestItemDtoBuilder.testItemDtoBuilder;
import static java.util.List.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderMapperServiceIntegrationTest {
    OrderRepository orderRepository = new OrderRepository();
    UserRepository userRepository = new UserRepository();
    UserMapper userMapper = new UserMapper(new AddressMapper(), new UserRoleMapper());
    Item item = itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(2.5)
            .build();
    ItemDto itemDto = new ItemDto("id", "name", "description", 2.5);

    @Mock
    ItemRepository itemRepository;
    @Mock
    ItemMapper itemMapper;
    @InjectMocks
    OrderMapper orderMapper = new OrderMapper(itemMapper, itemRepository);

    OrderService orderService = new OrderService(orderRepository, orderMapper, userRepository, userMapper);

    @Test
    void whenAddingOrder_returnsANewOrderDto() {
        Collection<Feature> features = List.of(Feature.GET_ITEM_BY_ID);
        Authentication authentication = new UsernamePasswordAuthenticationToken("sven@order.com", "awesome");
        when(itemRepository.getItemById("id")).thenReturn(item);
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        OrderDto orderDto = orderService.addOrder(authentication, "id", 1);
        assertThat(orderDto.getTotalAmount()).isEqualTo(2.5);
        assertThat(orderDto.getOrders().containsKey(itemDto));
        assertThat(orderDto.getShippingDate()).isEqualTo(LocalDate.now().plusDays(7));
    }
}