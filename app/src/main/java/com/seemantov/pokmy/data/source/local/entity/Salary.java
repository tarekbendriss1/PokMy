package com.seemantov.pokmy.data.source.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.converter.Converters;

import java.util.List;




@Entity
@TypeConverters(Converters.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Salary implements Cloneable, Parcelable {

    @JsonProperty("_id")
    private String _id;
    @JsonProperty("activityState")
    private ActivityState activityState;
    @JsonProperty("asset")
    private Asset asset;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ActivityState getActivityState() {
        return activityState;
    }

    public void setActivityState(ActivityState activityState) {
        this.activityState = activityState;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Salary() {

    }

    public Salary(String _id, ActivityState activityState, Asset asset) {

        this._id = _id;
        this.activityState = activityState;
        this.asset = asset;
    }

    public static final Creator<Salary> CREATOR = new Creator<Salary>() {

        @Override
        public Salary createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Salary[] newArray(int size) {
            return new Salary[0];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }


}

