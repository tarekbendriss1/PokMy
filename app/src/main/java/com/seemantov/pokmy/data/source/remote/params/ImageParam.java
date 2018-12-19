package com.seemantov.pokmy.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tarek Ben Driss on 23/03/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageParam {

    @JsonProperty("base")
    private String base;

    public ImageParam(String base) {
        this.base = base;
    }

    public ImageParam() {
    }
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "ImageParam{" +
                "base='" + base + '\'' +
                '}';
    }
}
