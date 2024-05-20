package com.example.Edu;

import java.io.Serializable;

public class User implements Serializable {

    String id;
    String date, place, titleAuthor, description, offlineOnline, link;


    String liked;  // New property for like status
    String imageUrl;

    // Default constructor, getters, and setters



    public User(){}
    public User(String id, String date, String place, String titleAuthor, String description, String offlineOnline, String link, String liked, String imageUrl) {
        this.id = id;
        this.date = date;
        this.place = place;
        this.titleAuthor = titleAuthor;
        this.description = description;
        this.offlineOnline = offlineOnline;
        this.link = link;
        this.liked = liked;
        this.imageUrl = imageUrl;
    }


    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitleAuthor() {
        return titleAuthor;
    }

    public void setTitleAuthor(String titleAuthor) {
        this.titleAuthor = titleAuthor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getOfflineOnline() {
//        return offlineOnline;
//    }

    public String getOfflineOnline() {
        return offlineOnline;
    }

    public void setOfflineOnline(String offlineOnline) {
        this.offlineOnline = offlineOnline;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        return titleAuthor.equals(user.titleAuthor);
//    }
//
//    @Override
//    public int hashCode() {
//        return titleAuthor.hashCode();
//    }