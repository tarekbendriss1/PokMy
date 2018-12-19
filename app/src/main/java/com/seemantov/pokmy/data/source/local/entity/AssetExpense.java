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
public class AssetExpense {

    @JsonProperty("date")
    private String date;
    @JsonProperty("expenseReportType")
    private String expenseReportType;
    @JsonProperty("company")
    private String company;
    @JsonProperty("payment")
    private String payment;
    @JsonProperty("paymentMethod")
    private String paymentMethod;
    @JsonProperty("amount")
    private int amount;


    @Override
    public String toString() {
        return "AssetExpense{" +
                "date='" + date + '\'' +
                ", expenseReportType='" + expenseReportType + '\'' +
                ", company='" + company + '\'' +
                ", payment='" + payment + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpenseReportType() {
        return expenseReportType;
    }

    public void setExpenseReportType(String expenseReportType) {
        this.expenseReportType = expenseReportType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AssetExpense() {
    }

    public AssetExpense(String date, String expenseReportType, String company, String payment, String paymentMethod, int amount) {

        this.date = date;
        this.expenseReportType = expenseReportType;
        this.company = company;
        this.payment = payment;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }
}
