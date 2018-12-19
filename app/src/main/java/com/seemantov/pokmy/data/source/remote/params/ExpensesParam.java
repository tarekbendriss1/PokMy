package com.seemantov.pokmy.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.entity.AssetExpense;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpensesParam {

    @JsonProperty("description")
    private String description;
    @JsonProperty("priority")
    private String priority;
    @JsonProperty("asset")
    private AssetExpense asset;


    @Override
    public String toString() {
        return "ExpensesParam{" +
                "description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", asset=" + asset +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public AssetExpense getAsset() {
        return asset;
    }

    public void setAsset(AssetExpense asset) {
        this.asset = asset;
    }

    public ExpensesParam() {

    }

    public ExpensesParam(String description, String priority, AssetExpense asset) {

        this.description = description;
        this.priority = priority;
        this.asset = asset;
    }
}