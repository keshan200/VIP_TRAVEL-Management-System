package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class DashBoardModle {

    private String carCount;
    private String suvCount;
    private String vanCount;
    private String employeeCount;
    private String customerCount;
    private  String reservationCount;

   /* public DashBoardModle(){

    }


    public DashBoardModle(String carCount, String suvCount, String vanCount, String employeeCount, String customerCount,  String reservationCount) {
        this.carCount = carCount;
        this.suvCount = suvCount;
        this.vanCount = vanCount;
        this.employeeCount = employeeCount;
        this.customerCount = customerCount;
        this.reservationCount =  reservationCount;
    }

    public String getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(String reservationCount) {
        this.reservationCount = reservationCount;
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
    }*/
}
