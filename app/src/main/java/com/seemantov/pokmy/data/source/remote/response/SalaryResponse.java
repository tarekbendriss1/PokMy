package com.seemantov.pokmy.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.entity.ActivityState;
import com.seemantov.pokmy.data.source.local.entity.Asset;
import com.seemantov.pokmy.data.source.local.entity.Image;

import java.util.List;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryResponse {

    @JsonProperty("_id")
    private String _id;
    @JsonProperty("activityState")
    private ActivityState activityState;
    @JsonProperty("asset")
    private Asset asset;

    @Override
    public String toString() {
        return "SalaryResponse{" +
                "_id='" + _id + '\'' +
                ", activityState=" + activityState +
                ", asset=" + asset +
                '}';
    }

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

    public SalaryResponse(String _id, ActivityState activityState, Asset asset) {

        this._id = _id;
        this.activityState = activityState;
        this.asset = asset;
    }

    public SalaryResponse() {
    }
}
