package com.switchfully.domain.item;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {
    private Map<Item, Integer> itemDatabase;

    public ItemRepository() {
        this.itemDatabase = new ConcurrentHashMap<>();
    }

    public Item addItem(Item item) {
        itemDatabase.put(item, itemDatabase.get(item) == null ? 1 : itemDatabase.get(item) + 1);
        return item;
    }

    public Map<String, Integer> viewAllItems() {
        return itemDatabase.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue));
    }
}
