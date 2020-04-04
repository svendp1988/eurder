package com.switchfully.domain.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.switchfully.domain.testbuilders.TestItemBuilder.testItemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void viewingAllItems_returnsMapOfItemNames_andNumberOfItemsInStock() {
        Item firstItemToAdd = testItemBuilder().build();
        Item secondItemToAdd = testItemBuilder().build();
        itemRepository.addItem(firstItemToAdd);
        itemRepository.addItem(secondItemToAdd);
        Map<String, Integer> actualItemMap = itemRepository.viewAllItems();
        assertThat(actualItemMap.containsKey(firstItemToAdd.getName()));
        assertThat(actualItemMap.containsValue(2));
    }

    @Test
    void getItemByName_returnsItemByLookingUpNameInDatabase() {
        Item item = testItemBuilder().build();
        itemRepository.addItem(item);
        assertEquals(item, itemRepository.getItemByName(item.getName()));
    }
}