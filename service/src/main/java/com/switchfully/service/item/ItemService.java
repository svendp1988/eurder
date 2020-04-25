package com.switchfully.service.item;

import com.switchfully.domain.exceptions.ItemNotFoundException;
import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.item.dto.UpdateItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto addItem(CreateItemDto createItemDto) {
        return itemMapper.toItemDto(itemRepository.save(itemMapper.toNewItem(createItemDto)));
    }

    @Query
    public List<ItemDto> findAll() {
        return itemMapper.toItemDto(itemRepository.findAll());
    }

    public ItemDto findById(long id) {
        return itemMapper.toItemDto(getItemOrThrowException(id));
    }

    private Item getItemOrThrowException(long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public ItemDto updateItem(UpdateItemDto updateItemDto) {
        Item item = getItemOrThrowException(updateItemDto.getId());
        item.setName(updateItemDto.getName());
        item.setDescription(updateItemDto.getDescription());
        item.setPrice(updateItemDto.getPrice());
        item.setAmount(updateItemDto.getAmount());
        itemRepository.save(item);
        return itemMapper.toItemDto(item);
    }
}
