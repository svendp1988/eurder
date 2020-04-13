package com.switchfully.service.order;

import com.fasterxml.jackson.annotation.JsonValue;
import com.switchfully.service.address.AddressDto;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.user.dto.UserDto;

public class Report {
    private final ItemDto itemDto;
    private final int amount;
    private final UserDto userDto;
    private final AddressDto addressDto;

    public Report(ItemDto itemDto, int amount, UserDto userDto, AddressDto addressDto) {
        this.itemDto = itemDto;
        this.amount = amount;
        this.userDto = userDto;
        this.addressDto = addressDto;
    }

    @Override
    @JsonValue
    public String toString() {
        return "Report{" +
                "itemId=" + itemDto.getId() + ", itemName=" + itemDto.getName() +
                ", amount=" + amount +
                ", address=" + userDto.getFirstName() + " " + userDto.getLastName() + " " +
                addressDto.getStreet() + " " + addressDto.getStreetNumber() + " " +
                addressDto.getPostalCode() + " " + addressDto.getCity();
    }
}
