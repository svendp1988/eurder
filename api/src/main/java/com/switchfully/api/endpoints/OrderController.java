package com.switchfully.api.endpoints;

import com.switchfully.service.order.OrderDto;
import com.switchfully.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = OrderController.ORDER_RESOURCE_PATH)
public class OrderController {
    public static final String ORDER_RESOURCE_PATH = "/orders";
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAuthority('ORDER_ITEM')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Placing an order", notes = "Customers can place orders.", response = OrderDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(Authentication authentication, @RequestBody String itemId, @RequestBody int amount) {
        LOGGER.info("Adding an order");
        return orderService.addOrder(authentication, itemId, amount);
    }

    @PreAuthorize("hasAuthority('SEE_REPORT')")
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Retrieving report of orders", notes = "Customers can retrieve a list of their orders", response = List.class)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getReportOfOrders(Authentication authentication) {
        LOGGER.info("Retrieving report of orders.");
        return orderService.getReportOfOrders(authentication);
    }
}
