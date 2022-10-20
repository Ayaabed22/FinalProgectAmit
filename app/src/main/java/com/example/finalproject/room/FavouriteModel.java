package com.example.finalproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "productDataBase")

public class FavouriteModel {
    @Override
    public String toString() {
        return "dataRoomBase{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private Integer id;

    @ColumnInfo
    @SerializedName("title")
    @Expose
    private String title;

    @ColumnInfo
    @SerializedName("price")
    @Expose
    private Double price;

    @ColumnInfo
    @SerializedName("image")
    @Expose
    private String image;

    public FavouriteModel(Integer id, String title, Double price, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
