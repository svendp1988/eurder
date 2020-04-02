package com.switchfully.domain.item;

import org.junit.jupiter.api.Test;

import static com.switchfully.domain.testbuilders.TestItemBuilder.testItemBuilder;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void itemShouldReturnTheCorrectValues() {
        Item item = testItemBuilder().build();
        assertEquals("name", item.getName());
        assertEquals("description", item.getDescription());
        assertEquals(0.0, item.getPrice());
    }
}