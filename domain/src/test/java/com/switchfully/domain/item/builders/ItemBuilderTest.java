package com.switchfully.domain.item.builders;

import org.junit.jupiter.api.Test;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemBuilderTest {
    @Test
    void itemBuilder_returnsCorrectValues() {
        ItemBuilder itemBuilder = itemBuilder()
                .withName("name")
                .withDescription("description")
                .withPrice(0.0);
        assertEquals("name", itemBuilder.getName());
        assertEquals("description", itemBuilder.getDescription());
        assertEquals(0.0, itemBuilder.getPrice());
    }
}