package com.switchfully.api.endpoints;

import com.fasterxml.jackson.annotation.JsonView;
import com.switchfully.service.user.CreateUserDto;
import com.switchfully.service.user.UserDto;
import com.switchfully.service.user.UserService;
import com.switchfully.service.user.View;
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
    public static final String USER_RESOURCE_PATH = "/customers";
    private final Logger loggerUser = LoggerFactory.getLogger(UserService.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Register as a member", notes = "Everyone can freely join Digibooky!", response = UserDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody CreateUserDto newCustomer) {
        loggerUser.info("Registering a new customer.");
        return userService.registerAsCustomer(newCustomer);
    }

    @JsonView(View.Public.class)
    @PreAuthorize("hasAuthority('VIEW_CUSTOMERS')")
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all registered members", notes = "A list of all the registered customers will be returned" , response = UserDto.class)
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserDto> getAllMembers() {
        loggerUser.info("Returning all members");
        return userService.viewAllCustomers();
    }

    @JsonView(View.Public.class)
    @PreAuthorize("hasAuthority('VIEW_CUSTOMERS')")
    @GetMapping(params = "{id}", produces = "application/json")
    @ApiOperation(value = "Get member by id.", notes = "Member with id will be returned" , response = UserDto.class)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getAllMembers(@RequestParam String id) {
        loggerUser.info("Returning member with id: " + id);
        return userService.getById(id);
    }
}
