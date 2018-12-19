package com.seemantov.pokmy.utils;


public class TopTitleEvent {
    private String title;
    private int id;

    public TopTitleEvent(String message) {
        this.title = message;
    }



    public TopTitleEvent(String message,int id) {
        this.title = message;
        this.id = id;
    }



    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }

}
