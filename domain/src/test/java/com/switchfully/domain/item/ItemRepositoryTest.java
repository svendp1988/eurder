package com.switchfully.domain.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.switchfully.domain.testbuilders.TestItemBuilder.testItemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository;
    @BeforeEach
    void setUp() {
        itemRepository = new ItemRepository();
    }

    @Test
    void addingAnItem_shouldReturnTheSameItem() {
        Item itemToAdd = testItemBuilder().build();
        Item actualItem = itemRepository.addItem(itemToAdd);
        assertEquals(itemToAdd, actualItem);
    }

    @Test
    void addingTheSameMoreThanOnce_shouldResultInAddingToCount_forValueInDatabase() {
        Item firstItemToAdd = testItemBuilder().build();
        Item secondItemToAdd = testItemBuilder().build();
        itemRepository.addItem(firstItemToAdd);
        itemRepository.addItem(secondItemToAdd);
        assertThat(itemRepository.viewAllItems().get(firstItemToAdd.getName())).isEqualTo(2);
    }
}