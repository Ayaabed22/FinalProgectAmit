package com.example.finalproject.model;

import com.google.gson.annotations.SerializedName;

public class Geolocation {
    @SerializedName("lat")
    private String lat;

    @SerializedName("long")
    private String jsonMemberLong;

    public Geolocation(String lat, String jsonMemberLong) {
        this.lat = lat;
        this.jsonMemberLong = jsonMemberLong;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getJsonMemberLong() {
        return jsonMemberLong;
    }

    public void setJsonMemberLong(String jsonMemberLong) {
        this.jsonMemberLong = jsonMemberLong;
    }
}
