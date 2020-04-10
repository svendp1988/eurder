package com.switchfully.domain.order;

import com.switchfully.domain.item.Item;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderTest {
    Item item = itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(2.5)
            .build();
    Map<Item, Integer> items = Map.of(item, 2);
    Order order;

    @Test
    void calculateTotalAmount_returnsTotalPriceOfItemsInOrder() {
        order = new Order(item, 2, null);
        assertThat(order.getTotalAmount()).isEqualTo(5);
    }

    @Test
    void shippingDateAndIdAreSet_uponInitialisationOfNewOrder() {
        order = new Order(item, 2, LocalDate.now());
        assertEquals(LocalDate.now(), order.getShippingDate());
        assertNotNull(order.getOrderId());
    }

    @Test
    void getItems_returnsMapOfItems() {
        order = new Order(item, 2, null);
        assertThat(order.getItem()).isEqualTo(item);
        assertEquals(2, order.getAmount());
    }
}