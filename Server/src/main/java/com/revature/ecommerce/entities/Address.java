package com.revature.ecommerce.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(name = "street_address1")
    private String streetAddress1;

    @Column(name = "street_address2", nullable = true)
    private String streetAddress2;

    @Column(nullable = true)
    private String apartment;

    @Column
    private String city;

    @Column(length = 2)
    private String state;

    @Column(length = 5)
    private String zipcode;

    public Address() {
    }

    public Address(String streetAddress1, String streetAddress2, String apartment, String city, String state, String zipcode) {
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Address(Integer addressId, String streetAddress1, String streetAddress2, String apartment, String city, String state, String zipcode) {
        this.addressId = addressId;
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) && Objects.equals(streetAddress1, address.streetAddress1) && Objects.equals(streetAddress2, address.streetAddress2) && Objects.equals(apartment, address.apartment) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, streetAddress1, streetAddress2, apartment, city, state, zipcode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetAddress1='" + streetAddress1 + '\'' +
                ", streetAddress2='" + streetAddress2 + '\'' +
                ", apartment='" + apartment + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
