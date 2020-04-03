package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto addItem(CreateItemDto createItemDto) {
        return itemMapper.toItemDto(itemRepository.addItem(itemMapper.toNewItem(createItemDto)));
    }

    public Map<String, Integer> viewAllItems() {
        return itemRepository.viewAllItems();
    }

    public Item getItemByName(String name) {
        return itemRepository.getItemByName(name);
    }
}
