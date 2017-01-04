package net.avantica.xinef.dapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReverseGeocodingEntity {
    @SerializedName("results")
    private List<ReverseGeocodingResult> results;
    @SerializedName("status")
    private String status;

    public List<ReverseGeocodingResult> getResults() {
        return results;
    }

    public void setResults(List<ReverseGeocodingResult> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
