package com.switchfully.api.endpoints;

import com.fasterxml.jackson.annotation.JsonView;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.UserService;
import com.switchfully.service.user.view.View;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(path = UserController.USER_RESOURCE_PATH)
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    public static final String USER_RESOURCE_PATH = "/customers";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Register as a customer", notes = "Everyone can register as a customer.", response = UserDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody CreateUserDto newCustomer) {
        LOGGER.info("Registering a new customer.");
        return userService.registerAsCustomer(newCustomer);
    }

    @JsonView(View.Public.class)
    @PreAuthorize("hasAuthority('VIEW_CUSTOMERS')")
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all registered customers", notes = "A list of all the registered customers will be returned" , response = UserDto.class)
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserDto> viewAllCustomers() {
        LOGGER.info("Returning all members");
        return userService.viewAllCustomers();
    }

    @JsonView(View.Public.class)
    @PreAuthorize("hasAuthority('VIEW_CUSTOMERS')")
    @GetMapping(params = "{id}", produces = "application/json")
    @ApiOperation(value = "Get customer by id.", notes = "Customer with id will be returned" , response = UserDto.class)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@RequestParam String id) {
        LOGGER.info("Returning customer with id: " + id);
        return userService.getById(id);
    }
}
