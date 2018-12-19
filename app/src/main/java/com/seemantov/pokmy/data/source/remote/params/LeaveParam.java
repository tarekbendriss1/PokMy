package com.seemantov.pokmy.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.entity.AssetAddLeave;
import com.seemantov.pokmy.data.source.local.entity.AssetExpense;

public class LeaveParam {

    @JsonProperty("asset")
    private AssetAddLeave asset;
    @JsonProperty("priority")
    private String priority;
    @JsonProperty("description")
    private String description;


    @Override
    public String toString() {
        return "LeaveParam{" +
                "asset=" + asset +
                ", priority='" + priority + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public AssetAddLeave getAsset() {
        return asset;
    }

    public void setAsset(AssetAddLeave asset) {
        this.asset = asset;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LeaveParam() {

    }

    public LeaveParam(AssetAddLeave asset, String priority, String description) {

        this.asset = asset;
        this.priority = priority;
        this.description = description;
    }
}