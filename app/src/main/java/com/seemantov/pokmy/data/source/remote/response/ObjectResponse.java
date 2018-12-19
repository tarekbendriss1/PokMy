package com.seemantov.pokmy.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tarek Ben Driss on 22/03/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectResponse {

    @JsonProperty("responseMessage")
    private String responseMessage;



    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ObjectResponse() {
    }

    public ObjectResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "ObjectResponse{" +
                "responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
