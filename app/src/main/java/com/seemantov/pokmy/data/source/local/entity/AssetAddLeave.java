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
public class AssetAddLeave {
    @JsonProperty("leaveType")
    private String leaveType;
    @JsonProperty("start")
    private StartEnd start;
    @JsonProperty("end")
    private StartEnd end;

    @Override
    public String toString() {
        return "AssetAddLeave{" +
                "leaveType='" + leaveType + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public StartEnd getStart() {
        return start;
    }

    public void setStart(StartEnd start) {
        this.start = start;
    }

    public StartEnd getEnd() {
        return end;
    }

    public void setEnd(StartEnd end) {
        this.end = end;
    }

    public AssetAddLeave() {

    }

    public AssetAddLeave(String leaveType, StartEnd start, StartEnd end) {

        this.leaveType = leaveType;
        this.start = start;
        this.end = end;
    }
}
