package com.example.finalproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.example.finalproject.R;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;

public class dataResponse implements Comparable<dataResponse> {


    @SerializedName("id")
    @Expose
    private Integer id;


    @SerializedName("title")
    @Expose
    public  String title;


    @SerializedName("price")
    @Expose
public Double price;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("category")
    @Expose
    private String category;


    @SerializedName("image")
    @Expose
   public String image;

    @SerializedName("rating")
    @Expose
    private Rating rating;

    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }


    public Comparator<dataResponse> productitle = new Comparator<dataResponse>() {


        @Override
        public int compare(dataResponse o1, dataResponse o2) {
            return o1.getTitle().compareTo(o2.getTitle());

        }
    };
    public Comparator<dataResponse> productRating = new Comparator<dataResponse>() {
        @Override
        public int compare(dataResponse o1, dataResponse o2) {
            return o1.rating.rate.compareTo(o2.rating.rate);
        }
    };

    @Override
    public int compareTo(dataResponse o) {
        return 0;
    }

    public class Rating {

        @SerializedName("rate")
        @Expose
        private Double rate;
        @SerializedName("count")
        @Expose
        private Integer count;

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

    }

    @Override
    public String toString() {
        return "dataResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", quantity=" + quantity +
                ", productitle=" + productitle +
                ", productRating=" + productRating +
                '}';
    }
}

