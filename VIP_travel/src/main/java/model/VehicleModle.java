package model;

public class VehicleModle {

    private  String vehicleID;
    private String regNo;
    private String  year;
    private String  vehicleName;
    private String  fuelType;

    private String   vehicleType;
    private double   cost;
    private String    availability;


    public VehicleModle() {}

    public VehicleModle(String vehicleName,String regNo,double cost) {
        this.vehicleName = vehicleName;
        this. regNo = regNo;
        this.cost = cost;
    }

    public VehicleModle(String vehicleID, double cost, String availability) {
        this.vehicleID = vehicleID;
        this.cost = cost;
        this.availability = availability;
    }

    public VehicleModle(String vehicleID, String regNo, String year, String vehicleName, String fuelType, String vehicleType, double cost, String availability) {
        this.vehicleID = vehicleID;
        this.regNo = regNo;
        this.year = year;
        this.vehicleName = vehicleName;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
        this.cost = cost;
        this.availability = availability;
    }


    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }


    @Override
    public String toString() {
        return "VehicleModle{" +
                "vehicleID='" + vehicleID + '\'' +
                ", regNo='" + regNo + '\'' +
                ", year='" + year + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", cost='" + cost + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}
