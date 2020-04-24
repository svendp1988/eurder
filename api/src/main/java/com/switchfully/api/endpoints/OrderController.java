package com.switchfully.api.endpoints;

import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.order.OrderDto;
import com.switchfully.service.order.OrderRequestDto;
import com.switchfully.service.order.OrderService;
import com.switchfully.service.order.Report;
//import io.swagger.annotations.ApiOperation;
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
//    @ApiOperation(value = "Placing an order", notes = "Customers can place orders.", response = OrderDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(Authentication authentication, @RequestBody OrderRequestDto orderRequest) {
        LOGGER.info("Adding an order");
        return orderService.addOrder(authentication, orderRequest);
    }

    @PreAuthorize("hasAuthority('SEE_REPORT')")
    @GetMapping(produces = "application/json")
//    @ApiOperation(value = "Retrieving report of orders", notes = "Customers can retrieve a list of their orders", response = List.class)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getReportOfOrders(Authentication authentication) {
        LOGGER.info("Retrieving report of orders.");
        return orderService.getReportOfOrders(authentication);
    }

    @PreAuthorize("hasAuthority('ORDER_ITEM')")
    @PostMapping(path = "/{orderId}", produces = "application/json")
//    @ApiOperation(value = "Reordering previous order.", notes = "Customers can reorder a previous order.", response = OrderDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reOrder(Authentication authentication, @PathVariable String orderId) {
        LOGGER.info("Reordering previous order.");
        return orderService.reOrder(authentication, orderId);
    }

    @PreAuthorize("hasAuthority('VIEW_ITEMS_SHIPPING_TODAY')")
    @GetMapping(path = "/report", produces = "application/json")
//    @ApiOperation(value = "Reporting items shipping today.", notes = "Admins can view a report of items shipping today.", response = List.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Report> viewItemsShippingToday() {
        LOGGER.info("Returning list of items shipping today.");
        return orderService.viewItemsShippingToday();
    }
}
