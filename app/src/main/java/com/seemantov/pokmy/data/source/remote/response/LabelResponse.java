package com.seemantov.pokmy.data.source.remote.response;

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
public class LabelResponse {

    @JsonProperty("_id")
    private String _id;
    @JsonProperty("label")
    private String label;

    @Override
    public String toString() {
        return "LabelResponse{" +
                "_id='" + _id + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LabelResponse() {

    }

    public LabelResponse(String _id, String label) {

        this._id = _id;
        this.label = label;
    }
}