package com.example.aman.olxclone.DummyData;

import java.io.Serializable;
import java.util.List;

public class Ads implements Serializable {
String title;
List<String> pictures;
String description;
String category;
String location;
String user_id;
String ad_id;
String price;
Integer views;
Integer likes;


    public Ads(String title, List<String> pictures, String description, String category, String location, String user_id, String ad_id,String price,Integer views,Integer likes) {
        this.title = title;
        this.pictures = pictures;
        this.description = description;
        this.category = category;
        this.location = location;
        this.user_id = user_id;
        this.ad_id = ad_id;
        this.price=price;
        this.views=views;
        this.likes=likes;
    }
    public void newView()
    {this.views++;}

    public void newLike()
    {this.likes++;}

    public void newunLike()
    {this.likes--;}

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    @Override
    public String toString() {
        return "Ads{" +
                "title='" + title + '\'' +
                ", pictures=" + pictures +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", user_id='" + user_id + '\'' +
                ", ad_id='" + ad_id + '\'' +
                '}';
    }
}
