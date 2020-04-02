package com.switchfully.api.endpoints;

import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Add an item.", notes = "Admins can add items.", response = ItemDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestBody CreateItemDto createItemDto) {
        LOGGER.info("Adding an item.");
        return itemService.addItem(createItemDto);
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all items", notes = "A list of all items will be returned" , response = Map.class)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Integer> viewAllItems() {
        LOGGER.info("Returning all items");
        return itemService.viewAllItems();
    }


}