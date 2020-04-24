package com.switchfully.api.endpoints;

import com.fasterxml.jackson.annotation.JsonView;
import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.item.dto.UpdateItemDto;
import com.switchfully.service.user.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = ItemController.ITEM_RESOURCE_PATH)
public class ItemController {
    public static final String ITEM_RESOURCE_PATH = "/items";
    private final static Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;


    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @JsonView(View.Public.class)
    @PreAuthorize("hasAuthority('ADD_ITEM')")
    @PostMapping(consumes = "application/json", produces = "application/json")
//    @ApiOperation(value = "Add an item.", notes = "Admins can add items.", response = ItemDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestBody CreateItemDto createItemDto) {
        LOGGER.info("Adding an item.");
        return itemService.addItem(createItemDto);
    }

    @GetMapping(produces = "application/json")
//    @ApiOperation(value = "Get all items", notes = "A list of all items will be returned" , response = Map.class)
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> viewAllItems() {
        LOGGER.info("Returning all items");
        return itemService.findAll();
    }

    @PreAuthorize("hasAuthority('GET_ITEM_BY_ID')")
    @GetMapping(params = "{id}", produces = "application/json")
//    @ApiOperation(value = "Getting item by id", notes = "Customers can get items by id.", response = ItemDto.class)
    @ResponseStatus(HttpStatus.OK)
    public ItemDto getItemById(@RequestParam long id) {
        LOGGER.info("Returning item by id.");
        return itemService.findById(id);
    }

    @PreAuthorize("hasAuthority('UPDATE_ITEM')")
    @PutMapping(consumes = "application/json", produces = "application/json")
//    @ApiOperation(value = "Updating item", notes = "Admins can update items.", response = ItemDto.class)
    @ResponseStatus(HttpStatus.OK)
    public ItemDto updateItem(@RequestBody UpdateItemDto updateItemDto) {
        LOGGER.info("Updating an item.");
        return itemService.updateItem(updateItemDto);
    }
}
