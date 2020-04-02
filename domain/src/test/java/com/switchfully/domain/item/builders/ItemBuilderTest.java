package com.switchfully.domain.item.builders;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.builders.UserBuilder;
import org.junit.jupiter.api.Test;

import static com.switchfully.domain.testbuilders.TestAddressBuilder.testAddressBuilder;
import static com.switchfully.domain.testbuilders.TestItemBuilder.testItemBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static org.junit.jupiter.api.Assertions.*;

class ItemBuilderTest {
    @Test
    void itemBuilder_returnsCorrectValues() {
        ItemBuilder itemBuilder = testItemBuilder();
        assertEquals("name", itemBuilder.getName());
        assertEquals("description", itemBuilder.getDescription());
        assertEquals(0.0, itemBuilder.getPrice());
        assertEquals(0, itemBuilder.getAmount());
    }
}