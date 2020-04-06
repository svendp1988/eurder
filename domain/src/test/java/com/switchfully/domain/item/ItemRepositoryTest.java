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
    void getItemById_returnsItemByLookingUpIdInDatabase() {
        Item item = testItemBuilder().build();
        itemRepository.addItem(item);
        assertEquals(item, itemRepository.getItemById(item.getId()));
    }

    @Test
    void decrementingLastItemInDatabase_leadsToValue0() {
        Item item = testItemBuilder().build();
        itemRepository.addItem(item);
        itemRepository.decrementItemAmount(item, 1);
        assertThat(itemRepository.viewAllItems().containsValue(0));
    }

    @Test
    void decrementingAmountInDatabase_canLeadToNegativeValue() {
        Item item = testItemBuilder().build();
        itemRepository.addItem(item);
        itemRepository.decrementItemAmount(item, 4);
        assertThat(itemRepository.viewAllItems().containsValue(-3));
    }

    @Test
    void getAmountOfItems_returnsItemsLeftInStock() {
        Item item = testItemBuilder().build();
        String id = item.getId();
        itemRepository.addItem(item);
        itemRepository.addItem(item);
        assertThat(itemRepository.getAmountOfItems(id)).isEqualTo(2);
    }
}