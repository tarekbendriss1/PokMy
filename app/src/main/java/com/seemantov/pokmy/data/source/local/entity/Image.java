package com.seemantov.pokmy.data.source.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import com.seemantov.pokmy.data.source.local.converter.Converters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@TypeConverters(Converters.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {
    @JsonProperty("_id")
    private String _id;
    @JsonProperty("subjectId")
    private String subjectId;
    @JsonProperty("subjectType")
    private String subjectType;
    @JsonProperty("contentType")
    private String contentType;

    @JsonProperty("type")
    private String type;
    @JsonProperty("extension")
    private String extension;
    @JsonProperty("storageId")
    private String storageId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("__v")
    private int __v;

    @JsonProperty("uploadedBy")
    private UploadedBy uploadedBy;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public UploadedBy getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(UploadedBy uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}
