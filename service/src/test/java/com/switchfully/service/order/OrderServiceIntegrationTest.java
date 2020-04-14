package com.switchfully.service.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.Order;
import com.switchfully.domain.order.OrderRepository;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.user.UserMapper;
import com.switchfully.service.user.role.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceIntegrationTest {
    OrderRepository orderRepository = new OrderRepository();
    UserRepository userRepository = new UserRepository();
    UserMapper userMapper = new UserMapper(new AddressMapper(), new UserRoleMapper());
    Authentication authentication = new UsernamePasswordAuthenticationToken("sven@order.com", "awesome");
    Item item = itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(2.5)
            .build();
    ItemDto itemDto = new ItemDto("id", "name", "description", 2.5, null);
    Order order = new Order(Map.of(item, 2));
    OrderRequestDto orderRequestDto = new OrderRequestDto(Map.of("id", 2));

    @Mock
    ItemMapper itemMapper;
    @InjectMocks
    OrderMapper orderMapper = new OrderMapper(itemMapper);

    ItemRepository itemRepository = mock(ItemRepository.class);
    @InjectMocks
    OrderService orderService = new OrderService(orderRepository, orderMapper, userRepository, userMapper, itemRepository, itemMapper);


    @Test
    void whenItemInStock_dateIsSetToNextDay() {
        Item soldItem = new Item(item);
        when(itemRepository.getAmountOfItems(item)).thenReturn(3);
        doNothing().when(itemRepository).decrementItemAmount(item, 1);
        orderService.setCorrectShippingDateAndDecrementAmountInDatabase(item, soldItem, 1);
        assertThat(soldItem.getShippingDate()).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void whenItemNotInStock_dateIsSetTo7DaysFromNow() {
        when(itemRepository.getAmountOfItems(item)).thenReturn(0);
        doNothing().when(itemRepository).decrementItemAmount(item, 1);
        Item soldItem = new Item(item);
        orderService.setCorrectShippingDateAndDecrementAmountInDatabase(item, soldItem, 1);
        assertThat(soldItem.getShippingDate()).isEqualTo(LocalDate.now().plusDays(7));
    }

    @Test
    void whenAddingOrder_returnsANewOrderDto() {
        when(itemRepository.getItemById("id")).thenReturn(item);
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        OrderDto orderDto = orderService.addOrder(authentication, orderRequestDto);
        assertThat(orderDto.getTotalAmount()).isEqualTo(5);
        assertThat(orderDto.getOrders().keySet()).contains(itemDto);
    }

    @Test
    void getReportOfOrders_returnsListOfOrdersForUser() {
        String userId = orderService.getUserId(authentication);
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        orderRepository.addOrder(userId, order);
        assertThat(orderService.getReportOfOrders(authentication)).containsExactly(orderMapper.toDto(order));
    }

    @Test
    void reOrderingItem_returnsDtoWithSameItemsAndAmountAsPreviousOrder() {
        String userId = orderService.getUserId(authentication);
        orderRepository.addOrder(userId, order);
        List<Order> orders = orderRepository.getReportOfOrders(userId);
        String foundorder = orders.stream()
                .filter(order1 -> order1.getOrderId().equals(order.getOrderId()))
                .findFirst()
                .orElseThrow()
                .getOrderId();
        OrderDto actualDto = orderService.reOrder(authentication, foundorder);
        OrderDto expectedDto = orderMapper.toDto(order);
        assertEquals(expectedDto.getOrders().keySet(), actualDto.getOrders().keySet());
        assertEquals(expectedDto.getOrders().get(itemDto), actualDto.getOrders().get(itemDto));
    }
}