package com.example.Edu;

import java.io.Serializable;

public class User implements Serializable {

    String date, place, titleAuthor, description, offlineOnline, link;

    public User(){}
    public User(String date, String place, String titleAuthor, String description, String offlineOnline, String link) {
        this.date = date;
        this.place = place;
        this.titleAuthor = titleAuthor;
        this.description = description;
        this.offlineOnline = offlineOnline;
        this.link = link;
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
}
