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
    private String populatedCenter;
    private String formulatingUnit;
    private String sector;
    private String folder;
    private String executor;
    private String level;
    private String snipCode;
    private String uniqueCode;
    private String name;
    private String function;
    private String program;
    private String subprogram;
    private String fundingSource;
    private String registrationDate;
    private String situation;
    private String state;
    private String closed;
    private String viabDate;
    private String viableAmount;
    private String beneficiary;
    private String objective;
    private String alternative;
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
        populatedCenter = in.readString();
        formulatingUnit = in.readString();
        sector = in.readString();
        folder = in.readString();
        executor = in.readString();
        level = in.readString();
        snipCode = in.readString();
        uniqueCode = in.readString();
        name = in.readString();
        function = in.readString();
        program = in.readString();
        subprogram = in.readString();
        fundingSource = in.readString();
        registrationDate = in.readString();
        situation = in.readString();
        state = in.readString();
        closed = in.readString();
        viabDate = in.readString();
        viableAmount = in.readString();
        beneficiary = in.readString();
        objective = in.readString();
        alternative = in.readString();
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
        parcel.writeString(populatedCenter);
        parcel.writeString(formulatingUnit);
        parcel.writeString(sector);
        parcel.writeString(folder);
        parcel.writeString(executor);
        parcel.writeString(level);
        parcel.writeString(snipCode);
        parcel.writeString(uniqueCode);
        parcel.writeString(name);
        parcel.writeString(function);
        parcel.writeString(program);
        parcel.writeString(subprogram);
        parcel.writeString(fundingSource);
        parcel.writeString(registrationDate);
        parcel.writeString(situation);
        parcel.writeString(state);
        parcel.writeString(closed);
        parcel.writeString(viabDate);
        parcel.writeString(viableAmount);
        parcel.writeString(beneficiary);
        parcel.writeString(objective);
        parcel.writeString(alternative);
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

    public String getPopulatedCenter() {
        return populatedCenter;
    }

    public void setPopulatedCenter(String populatedCenter) {
        this.populatedCenter = populatedCenter;
    }

    public String getFormulatingUnit() {
        return formulatingUnit;
    }

    public void setFormulatingUnit(String formulatingUnit) {
        this.formulatingUnit = formulatingUnit;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSnipCode() {
        return snipCode;
    }

    public void setSnipCode(String snipCode) {
        this.snipCode = snipCode;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSubprogram() {
        return subprogram;
    }

    public void setSubprogram(String subprogram) {
        this.subprogram = subprogram;
    }

    public String getFundingSource() {
        return fundingSource;
    }

    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getViabDate() {
        return viabDate;
    }

    public void setViabDate(String viabDate) {
        this.viabDate = viabDate;
    }

    public String getViableAmount() {
        return viableAmount;
    }

    public void setViableAmount(String viableAmount) {
        this.viableAmount = viableAmount;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
