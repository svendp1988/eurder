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

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    ItemDto itemDto = new ItemDto("id", "name", "description", 2.5);
    Order order = new Order(item, 2, LocalDate.now());
    OrderRequestDto orderRequestDto = new OrderRequestDto("id", 1);

    @Mock
    ItemMapper itemMapper;
    @InjectMocks
    OrderMapper orderMapper = new OrderMapper(itemMapper);

    @Mock
    ItemRepository itemRepository;
    @InjectMocks
    OrderService orderService = new OrderService(orderRepository, orderMapper, userRepository, userMapper, itemRepository);


    @Test
    void whenItemInStock_dateIsSetToNextDay() {
        when(itemRepository.getAmountOfItems(item)).thenReturn(3);
        assertThat(orderService.setCorrectShippingDateAndDecrementAmountInDatabase(item, 1)).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void whenItemNotInStock_dateIsSetTo7DaysFromNow() {
        when(itemRepository.getAmountOfItems(item)).thenReturn(0);
        assertThat(orderService.setCorrectShippingDateAndDecrementAmountInDatabase(item, 2)).isEqualTo(LocalDate.now().plusDays(7));
    }

    @Test
    void whenAddingOrder_returnsANewOrderDto() {
        when(itemRepository.getItemById("id")).thenReturn(item);
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        OrderDto orderDto = orderService.addOrder(authentication, orderRequestDto);
        assertThat(orderDto.getTotalAmount()).isEqualTo(2.5);
        assertThat(orderDto.getItemDto()).isEqualTo(itemDto);
        assertThat(orderDto.getShippingDate()).isEqualTo(LocalDate.now().plusDays(7));
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
        assertEquals(expectedDto.getItemDto(), actualDto.getItemDto());
        assertEquals(expectedDto.getAmount(), actualDto.getAmount());
    }
}