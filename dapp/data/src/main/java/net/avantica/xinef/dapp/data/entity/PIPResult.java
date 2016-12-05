package net.avantica.xinef.dapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PIPResult {
    @SerializedName("result")
    Result result;
    @SerializedName("endpoint")
    String endpoint;
    @SerializedName("description")
    String description;
    //parameters;
    @SerializedName("tags")
    List<String> tags;
    @SerializedName("timestamp")
    String timestamp;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("title")
    String title;
    @SerializedName("modified_at")
    String modified_at;
    @SerializedName("category_id")
    long category_id;
    @SerializedName("link")
    String link;
    @SerializedName("user")
    String user;
    @SerializedName("guid")
    String guid;
    @SerializedName("category_name")
    String category_name;

    public PIPResult() {
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
