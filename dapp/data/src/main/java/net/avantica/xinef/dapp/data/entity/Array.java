package net.avantica.xinef.dapp.data.entity;

import com.google.gson.annotations.SerializedName;

public class Array {

    @SerializedName("fStr")
    String fStr;
    @SerializedName("fHeader")
    boolean fHeader;
    @SerializedName("fType")
    String fType;

    public Array() {
    }

    public String getfStr() {
        return fStr;
    }

    public void setfStr(String fStr) {
        this.fStr = fStr;
    }

    public boolean isfHeader() {
        return fHeader;
    }

    public void setfHeader(boolean fHeader) {
        this.fHeader = fHeader;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }
}
