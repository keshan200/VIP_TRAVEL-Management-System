package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.TM.CartTM;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDetailsModle extends CartTM {


    // vehicle ID autoSave  from RegNo;
    private String reservationID;
    private String cusNIC;//not in
    private String cusName;//not in
    private String vehicleName;//not in,use for get vehicle RegNo , ID,& cost perDay
    private String RegNo;
    private Double costPerDay;//not in ,use for calculalt netTotal
    private LocalDate startDate;
    private LocalDate endDate;
    private int  daysCount;//not in use for calculate NetTotal
    private double  totalCost;


    public BookingDetailsModle(String regNo,String reservationID, LocalDate startDate,LocalDate endDate, double totalCost,int daysCount) {
        this.RegNo = regNo;
        this.reservationID = reservationID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysCount= daysCount;
        this.totalCost = totalCost;
    }


    public BookingDetailsModle(String reservationID, String RegNo, LocalDate startDate, LocalDate endDate, int daysCount, double totalCost) {
        this.reservationID = reservationID;
        this.RegNo = RegNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysCount = daysCount;
        this.totalCost = totalCost;
    }
}
