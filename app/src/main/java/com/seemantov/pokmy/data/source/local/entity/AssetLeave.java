package com.seemantov.pokmy.data.source.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seemantov.pokmy.data.source.local.converter.Converters;


@TypeConverters(Converters.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetLeave {

    @JsonProperty("leaveTypeId")
    private String leaveTypeId;
    @JsonProperty("leaveTypeLabel")
    private String leaveTypeLabel;
    @JsonProperty("start")
    private StartEnd start;
    @JsonProperty("end")
    private StartEnd end;

    public AssetLeave() {
    }

    @Override
    public String toString() {
        return "AssetLeave{" +
                "leaveTypeId='" + leaveTypeId + '\'' +
                ", leaveTypeLabel='" + leaveTypeLabel + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public AssetLeave(String leaveTypeId, String leaveTypeLabel, StartEnd start, StartEnd end) {
        this.leaveTypeId = leaveTypeId;
        this.leaveTypeLabel = leaveTypeLabel;
        this.start = start;
        this.end = end;
    }

    public String getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(String leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveTypeLabel() {
        return leaveTypeLabel;
    }

    public void setLeaveTypeLabel(String leaveTypeLabel) {
        this.leaveTypeLabel = leaveTypeLabel;
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
}
