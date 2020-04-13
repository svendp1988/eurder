package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.order.OrderRepository;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.item.dto.UpdateItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public ItemDto getItemById(String id) {
        return itemMapper.toItemDto(itemRepository.getItemById(id));
    }

    public ItemDto updateItem(UpdateItemDto updateItemDto) {
        Item item = itemRepository.getItemById(updateItemDto.getId());
        item.setName(updateItemDto.getName());
        item.setDescription(updateItemDto.getDescription());
        item.setPrice(updateItemDto.getPrice());
        return itemMapper.toItemDto(item);
    }
}
