package com.seemantov.pokmy.data.source.local.entity;


import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.converter.Converters;


@TypeConverters(Converters.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Leave implements Cloneable, Parcelable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("activityStateId")
    private String activityStateId;
    @JsonProperty("activityStateLabel")
    private String activityStateLabel;
    @JsonProperty("type")
    private String type;
    @JsonProperty("asset")
    private AssetLeave asset;

    @JsonProperty("createdBy")
    private UploadedBy createdBy;

    public Leave(String id, String description, String activityStateId, String activityStateLabel, String type, AssetLeave asset, UploadedBy createdBy, AssetLeave asset1) {
        this.id = id;
        this.description = description;
        this.activityStateId = activityStateId;
        this.activityStateLabel = activityStateLabel;
        this.type = type;
        this.asset = asset;
        this.createdBy = createdBy;
        this.asset = asset1;
    }



    public UploadedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UploadedBy createdBy) {
        this.createdBy = createdBy;
    }

    public AssetLeave getAsset() {
        return asset;
    }

    public void setAsset(AssetLeave asset) {
        this.asset = asset;
    }

    public Leave(String id, String description, String activityStateId, String activityStateLabel, String type, UploadedBy createdBy, AssetLeave asset) {

        this.id = id;
        this.description = description;
        this.activityStateId = activityStateId;
        this.activityStateLabel = activityStateLabel;
        this.type = type;
        this.createdBy = createdBy;
        this.asset = asset;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivityStateId() {
        return activityStateId;
    }

    public void setActivityStateId(String activityStateId) {
        this.activityStateId = activityStateId;
    }

    public String getActivityStateLabel() {
        return activityStateLabel;
    }

    public void setActivityStateLabel(String activityStateLabel) {
        this.activityStateLabel = activityStateLabel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Leave() {
    }



    public static final Creator<Leave> CREATOR = new Creator<Leave>() {


        @Override
        public Leave createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Leave[] newArray(int size) {
            return new Leave[0];
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

