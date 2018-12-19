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
public class ActivityState {


    @JsonProperty("label ")
    private String label ;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ActivityState() {

    }

    public ActivityState(String label) {

        this.label = label;
    }
}
