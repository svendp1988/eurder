package com.switchfully.domain.order;

import com.switchfully.domain.item.Item;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderRepositoryTest {
    Item item = itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(2.5)
            .build();
    Order order = new Order(item, 2, LocalDate.now());
    OrderRepository orderRepository = new OrderRepository();

    @Test
    void addingAnOrder_addsOrderToListOfOrders_inMapPerUser_andReturnsOrder() {
        assertEquals(order, orderRepository.addOrder("id", order));
    }

    @Test
    void getReportOfOrders_returnsListOfOrders_forUserId() {
        orderRepository.addOrder("id", order);
        List<Order> actualOrders = orderRepository.getReportOfOrders("id");
        assertThat(actualOrders).containsExactly(order);
    }
}