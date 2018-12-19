package com.seemantov.pokmy.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.entity.SalaryAdvanceAsset;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryAdvanceParam {

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("asset")
    private SalaryAdvanceAsset asset;

    @JsonProperty("description")
    private String description;

    @Override
    public String toString() {
        return "SalaryAdvanceParam{" +
                "priority='" + priority + '\'' +
                ", asset=" + asset +
                ", description='" + description + '\'' +
                '}';
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public SalaryAdvanceAsset getAsset() {
        return asset;
    }

    public void setAsset(SalaryAdvanceAsset asset) {
        this.asset = asset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SalaryAdvanceParam(String priority, SalaryAdvanceAsset asset, String description) {

        this.priority = priority;
        this.asset = asset;
        this.description = description;
    }

    public SalaryAdvanceParam() {
    }
}
