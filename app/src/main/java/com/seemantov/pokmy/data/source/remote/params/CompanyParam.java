package com.seemantov.pokmy.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.entity.AssetExpense;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyParam {

    @JsonProperty("company")
    private String company;

    @Override
    public String toString() {
        return "CompanyParam{" +
                "company='" + company + '\'' +
                '}';
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public CompanyParam() {

    }

    public CompanyParam(String company) {

        this.company = company;
    }
}