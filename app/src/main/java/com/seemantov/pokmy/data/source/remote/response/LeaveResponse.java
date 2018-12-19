package com.seemantov.pokmy.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.entity.AssetLeave;
import com.seemantov.pokmy.data.source.local.entity.UploadedBy;





@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaveResponse {

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

    public AssetLeave getAsset() {
        return asset;
    }

    public void setAsset(AssetLeave asset) {
        this.asset = asset;
    }

    public LeaveResponse(String id, String description, String activityStateId, String activityStateLabel, String type, AssetLeave asset, UploadedBy createdBy) {

        this.id = id;
        this.description = description;
        this.activityStateId = activityStateId;
        this.activityStateLabel = activityStateLabel;
        this.type = type;
        this.asset = asset;
        this.createdBy = createdBy;
    }


    @Override
    public String toString() {
        return "LeaveResponse{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", activityStateId='" + activityStateId + '\'' +
                ", activityStateLabel='" + activityStateLabel + '\'' +
                ", type='" + type + '\'' +
                ", createdBy=" + createdBy +
                '}';
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

    public UploadedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UploadedBy createdBy) {
        this.createdBy = createdBy;
    }

    public LeaveResponse() {

    }

    public LeaveResponse(String id, String description, String activityStateId, String activityStateLabel, String type, UploadedBy createdBy) {

        this.id = id;
        this.description = description;
        this.activityStateId = activityStateId;
        this.activityStateLabel = activityStateLabel;
        this.type = type;
        this.createdBy = createdBy;
    }
}
