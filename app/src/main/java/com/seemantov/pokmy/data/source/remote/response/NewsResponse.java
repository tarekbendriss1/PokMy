package com.seemantov.pokmy.data.source.remote.response;

import com.seemantov.pokmy.data.source.local.entity.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsResponse {

    @JsonProperty("_id")
    private String _id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("label")
    private String label;
    @JsonProperty("Images")
    private List<Image> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public NewsResponse() {
    }

    public NewsResponse(String _id, String description, String label, List<Image> images) {
        this._id = _id;
        this.description = description;
        this.label = label;
        this.images = images;
    }
}
