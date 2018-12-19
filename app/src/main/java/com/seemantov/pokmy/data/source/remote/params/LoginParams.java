package com.seemantov.pokmy.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tarek Ben Driss on 02/03/2018.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginParams {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;


    public LoginParams() {
    }

    public LoginParams(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public String toString() {
        return "LoginParams{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}