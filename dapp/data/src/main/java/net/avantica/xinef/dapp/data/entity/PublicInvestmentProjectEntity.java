package net.avantica.xinef.dapp.data.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.avantica.xinef.dapp.data.repository.AppDataBase;

@Table(database = AppDataBase.class, name = "PublicInvestmentProject")
public class PublicInvestmentProjectEntity extends BaseModel {
    @Column(name = "Id")
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column(name = "Department")
    private String department;
    @Column(name = "Province")
    private String province;
    @Column(name = "District")
    private String district;
    @Column(name = "ZipCode")
    private String zipCode;
    @Column(name = "Latitude")
    private String latitude;
    @Column(name = "Longitude")
    private String longitude;
    @Column(name = "PopulatedCenter")
    private String populatedCenter;
    @Column(name = "FormulatingUnit")
    private String formulatingUnit;
    @Column(name = "Sector")
    private String sector;
    @Column(name = "Folder")
    private String folder;
    @Column(name = "Executor")
    private String executor;
    @Column(name = "Level")
    private String level;
    @Column(name = "SnipCode")
    private String snipCode;
    @Column(name = "UniqueCode")
    private String uniqueCode;
    @Column(name = "Name")
    private String name;
    @Column(name = "Function")
    private String function;
    @Column(name = "Program")
    private String program;
    @Column(name = "Subprogram")
    private String subprogram;
    @Column(name = "FundingSource")
    private String fundingSource;
    @Column(name = "RegistrationDate")
    private String registrationDate;
    @Column(name = "Situation")
    private String situation;
    @Column(name = "State")
    private String state;
    @Column(name = "Closed")
    private String closed;
    @Column(name = "ViabDate")
    private String viabDate;
    @Column(name = "ViableAmount")
    private String viableAmount;
    @Column(name = "Beneficiary")
    private String beneficiary;
    @Column(name = "Objective")
    private String objective;
    @Column(name = "Alternative")
    private String alternative;
    @Column(name = "Cost")
    private String cost;

    public PublicInvestmentProjectEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
