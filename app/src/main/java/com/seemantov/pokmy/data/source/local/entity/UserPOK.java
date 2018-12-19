package com.seemantov.pokmy.data.source.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity



public class UserPOK {

    @NonNull
    @PrimaryKey
    private int id;
    @JsonProperty("__v")
    private String __v;
    @JsonProperty("token")
    private String token;
    @JsonProperty("userKey")
    private String userKey;
    @JsonProperty("_id")
    private String _id;
    @JsonProperty("updated")
    private String updated;

    public UserPOK() {
    }

    @Override
    public String toString() {
        return "UserPOK{" +
                "id=" + id +
                ", __v='" + __v + '\'' +
                ", token='" + token + '\'' +
                ", userKey='" + userKey + '\'' +
                ", _id='" + _id + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
