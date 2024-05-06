package model;

import java.time.LocalDate;



public class InsuranceModle {

    private String insuranceID;
    private String companyName;
    private String type;
    private String endDate;
    private String regNO;
   private String vehicleID;


    public InsuranceModle() {}

    public InsuranceModle(String insuranceID, String companyName, String type, String endDate, String regNO, String vehicleID) {
        this.insuranceID = insuranceID;
        this.companyName = companyName;
        this.type = type;
        this.endDate = endDate;
        this.regNO = regNO;
        this.vehicleID = vehicleID;
    }

    public InsuranceModle(String insuranceID, String companyName, String type, String endDate) {
       this. insuranceID= insuranceID;
        this.companyName = companyName;
        this.type = type;
        this.endDate = endDate;
    }

    public InsuranceModle(String insuranceID, String companyName, String type, String endDate, String regNO) {
        this.insuranceID = insuranceID;
        this.companyName = companyName;
        this.type = type;
        this.endDate = endDate;
        this.regNO = regNO;
    }


    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRegNO() {
        return regNO;
    }

    public void setRegNO(String regNO) {
        this.regNO = regNO;
    }

    @Override
    public String toString() {
        return "InsuranceModle{" +
                "insuranceID='" + insuranceID + '\'' +
                ", companyName='" + companyName + '\'' +
                ", type='" + type + '\'' +
                ", endDate='" + endDate + '\'' +
                ", regNO='" + regNO + '\'' +
                '}';
    }
}
