package com.seemantov.pokmy.data.source.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.converter.Converters;

@Entity
@TypeConverters(Converters.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StartEnd {

    @JsonProperty("halfDay")
    private String halfDay;
    @JsonProperty("date")
    private String date;



    @Override
    public String toString() {
        return "StartEnd{" +
                "halfDay='" + halfDay + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(String halfDay) {
        this.halfDay = halfDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        String y,m,d;
        y=date.substring(0,4);
        m=date.substring(5,7);
        d=date.substring(8,10);
        this.date = y+"-"+m+"-"+d;
        //this.date = date;
    }

    public StartEnd() {
    }

    public StartEnd(String halfDay, String date) {

        this.halfDay = halfDay;
        this.date = date;
    }
}
