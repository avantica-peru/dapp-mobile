package net.avantica.xinef.dapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PublicInvestmentProjectModel implements Parcelable {
    private String department;
    private String province;
    private String district;
    private String zipCode;
    private String latitude;
    private String longitude;
    private String name;
    private String function;
    private String uniqueCode;
    private String snipCode;
    private String cost;

    public PublicInvestmentProjectModel() {
    }

    public PublicInvestmentProjectModel(Parcel in) {
        department = in.readString();
        province = in.readString();
        district = in.readString();
        zipCode = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        name = in.readString();
        function = in.readString();
        uniqueCode = in.readString();
        snipCode = in.readString();
        cost = in.readString();
    }

    public static final Creator<PublicInvestmentProjectModel> CREATOR = new Creator<PublicInvestmentProjectModel>() {
        @Override
        public PublicInvestmentProjectModel createFromParcel(Parcel parcel) {
            return new PublicInvestmentProjectModel(parcel);
        }

        @Override
        public PublicInvestmentProjectModel[] newArray(int size) {
            return new PublicInvestmentProjectModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(department);
        parcel.writeString(province);
        parcel.writeString(district);
        parcel.writeString(zipCode);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(name);
        parcel.writeString(function);
        parcel.writeString(uniqueCode);
        parcel.writeString(snipCode);
        parcel.writeString(cost);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getSnipCode() {
        return snipCode;
    }

    public void setSnipCode(String snipCode) {
        this.snipCode = snipCode;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
