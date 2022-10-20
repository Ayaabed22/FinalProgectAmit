package com.example.finalproject.model;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("zipcode")
    private String zipcode;

    @SerializedName("number")
    private int number;

    @SerializedName("city")
    private String city;

    @SerializedName("street")
    private String street;

    @SerializedName("geolocation")
    private Geolocation geolocation;

    public Address(String zipcode, int number, String city, String street, Geolocation geolocation) {
        this.zipcode = zipcode;
        this.number = number;
        this.city = city;
        this.street = street;
        this.geolocation = geolocation;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }
}
