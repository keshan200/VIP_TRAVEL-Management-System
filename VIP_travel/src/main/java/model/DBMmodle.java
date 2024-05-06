package model;

public class DBMmodle {
    private String carCount;
    private String suvCount;
    private String vanCount;

    private String employeeCount;
    private String customerCount;

    public DBMmodle(){

    }


    public DBMmodle(String carCount, String suvCount, String vanCount, String employeeCount, String customerCount) {
        this.carCount = carCount;
        this.suvCount = suvCount;
        this.vanCount = vanCount;
        this.employeeCount = employeeCount;
        this.customerCount = customerCount;
    }


    public String getCarCount() {
        return carCount;
    }

    public void setCarCount(String carCount) {
        this.carCount = carCount;
    }

    public String getSuvCount() {
        return suvCount;
    }

    public void setSuvCount(String suvCount) {
        this.suvCount = suvCount;
    }

    public String getVanCount() {
        return vanCount;
    }

    public void setVanCount(String vanCount) {
        this.vanCount = vanCount;
    }

    public String getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(String employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(String customerCount) {
        this.customerCount = customerCount;
    }


    @Override
    public String toString() {
        return "DashBoardModle{" +
                "carCount='" + carCount + '\'' +
                ", suvCount='" + suvCount + '\'' +
                ", vanCount='" + vanCount + '\'' +
                ", employeeCount='" + employeeCount + '\'' +
                ", customerCount='" + customerCount + '\'' +
                '}';
    }
}
