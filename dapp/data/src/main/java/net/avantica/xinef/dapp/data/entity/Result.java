package net.avantica.xinef.dapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("fLength")
    long fLength;
    @SerializedName("fType")
    String fType;
    @SerializedName("fTimestamp")
    long fTimestamp;
    @SerializedName("fArray")
    List<Array> fArray;
    @SerializedName("fRows")
    int fRows;
    @SerializedName("fCols")
    int fCols;

    public Result() {
    }

    public long getfLength() {
        return fLength;
    }

    public void setfLength(long fLength) {
        this.fLength = fLength;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public long getfTimestamp() {
        return fTimestamp;
    }

    public void setfTimestamp(long fTimestamp) {
        this.fTimestamp = fTimestamp;
    }

    public List<Array> getfArray() {
        return fArray;
    }

    public void setfArray(List<Array> fArray) {
        this.fArray = fArray;
    }

    public int getfRows() {
        return fRows;
    }

    public void setfRows(int fRows) {
        this.fRows = fRows;
    }

    public int getfCols() {
        return fCols;
    }

    public void setfCols(int fCols) {
        this.fCols = fCols;
    }
}
