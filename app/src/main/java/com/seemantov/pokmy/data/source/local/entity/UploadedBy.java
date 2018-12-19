package com.seemantov.pokmy.data.source.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import com.seemantov.pokmy.data.source.local.converter.Converters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@TypeConverters(Converters.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadedBy {
    @JsonProperty("date")
    private String date;
    @JsonProperty("user")
    private String user;

    @Override
    public String toString() {
        return "UploadedBy{" +
                "date='" + date + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UploadedBy() {

    }

    public UploadedBy(String date, String user) {

        this.date = date;
        this.user = user;
    }
}
