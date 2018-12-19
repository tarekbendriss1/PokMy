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
public class SalaryAdvanceAsset {

    @JsonProperty("date")
    private String date;

    @JsonProperty("amount")
    private int amount;

    @Override
    public String toString() {
        return "SalaryAdvanceAsset{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public SalaryAdvanceAsset() {

    }

    public SalaryAdvanceAsset(String date, int amount) {

        this.date = date;
        this.amount = amount;
    }
}
