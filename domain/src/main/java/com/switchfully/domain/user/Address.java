package com.switchfully.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.switchfully.domain.user.builders.AddressBuilder;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(generator = "address_id_seq", strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long id;
    @Column(name = "street")
    private String street;
    @Column(name = "street_number")
    private String streetNumber;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
    private String city;

    public Address() {
    }

    public Address(AddressBuilder addressBuilder) {
        street = addressBuilder.getStreet();
        streetNumber = addressBuilder.getStreetNumber();
        postalCode = addressBuilder.getPostalCode();
        city = addressBuilder.getCity();
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(streetNumber, address.streetNumber) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, postalCode, city);
    }
}