package com.seemantov.pokmy.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;




@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginPokResponse {

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

    @Override
    public String toString() {
        return "LoginPokResponse{" +
                "__v='" + __v + '\'' +
                ", token='" + token + '\'' +
                ", userKey='" + userKey + '\'' +
                ", _id='" + _id + '\'' +
                ", updated=" + updated +
                '}';
    }
}
