package net.avantica.xinef.dapp.data.entity;

import com.google.gson.annotations.SerializedName;

public class PublicInvestmentProjectEntity {
    @SerializedName("departamento")
    private String department;
    @SerializedName("provincia")
    private String province;
    @SerializedName("distrito")
    private String district;
    @SerializedName("ubigeo")
    private String ubigeo;
    @SerializedName("latitud")
    private String latitude;
    @SerializedName("longitud")
    private String longitude;
    @SerializedName("nombre")
    private String name;
    @SerializedName("funcion")
    private String function;
    @SerializedName("codigoUnico")
    private String uniqueCode;
    @SerializedName("codigoSnip")
    private String snipCode;
    @SerializedName("costo")
    private String cost;

    public PublicInvestmentProjectEntity() {
        //TODO edward.carrion mock data, remove this data
        department = "LIMA";
        province = "LIMA";
        district = "LIMA";
        ubigeo = "150101";
        latitude = "35";
        longitude = "35";
        name = "MEJORAMIENTO DEL TECHO Y LOSA DEPORTIVA DE LA INSTITUCIÓN EDUCATIVA VIRGEN DE ASUNTA CHACHAPOYAS, PROVINCIA DE CHACHAPOYAS - AMAZONAS";
        function = "EDUCACIÓN";
        uniqueCode = "2295844";
        snipCode = "338552";
        cost = "486,550.05";
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

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
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
