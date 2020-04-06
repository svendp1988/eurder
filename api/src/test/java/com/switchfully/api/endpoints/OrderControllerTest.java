package com.switchfully.api.endpoints;

import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.order.OrderDto;
import com.switchfully.service.order.OrderService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    OrderDto order = new OrderDto("orderId", Map.of(new ItemDto("id", "name", "description", 2.5), 2), LocalDate.now(), 5);
    List<OrderDto> orders = List.of(order);
    Authentication authentication = new UsernamePasswordAuthenticationToken("sven@order.com", "awesome");

    OrderService orderService = mock(OrderService.class);

    @InjectMocks
    OrderController orderController = new OrderController(orderService);

    @Test
    void addOrder_returnsOrderDto() {
        when(orderService.addOrder(authentication, "id", 2)).thenReturn(order);
        OrderDto actualOrderDto = orderController.addOrder(authentication, "id", 2);
        assertEquals(order, actualOrderDto);
    }

    @Test
    void getReportOfOrders_returnsListOfOrdersForAuthenticatedUser() {
        when(orderService.getReportOfOrders(authentication)).thenReturn(orders);
        List<OrderDto> actualResult = orderController.getReportOfOrders(authentication);
        assertEquals(orders, actualResult);
    }
}