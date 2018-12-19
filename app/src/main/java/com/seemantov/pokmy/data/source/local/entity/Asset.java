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
public class Asset {

    @JsonProperty("amount")
    private String amount;
    @JsonProperty("date")
    private String date;
    @JsonProperty("paid")
    private String paid;
    @JsonProperty("paymentDate")
    private String paymentDate;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String y,m,d;
        y=date.substring(0,4);
        m=date.substring(5,7);
        d=date.substring(8,10);
        this.date = y+"-"+m+"-"+d;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Asset(String amount, String date, String paid, String paymentDate) {
        this.amount = amount;
        this.date = date;
        this.paid = paid;
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", paid='" + paid + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Asset() {

    }
}
