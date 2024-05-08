package model.TM;


import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTM {

    private String reservationID;
    private String regNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private int daysCount;
    private double totalCost;
    private JFXButton btnRemove;

    private  String NIC;
    private  String cusName;
    private  String vehicleName;
    private  Double costPerDay;

    public CartTM(String reservationID, String regNo, LocalDate startDate, LocalDate endDate, int daysCount, double totalCost, JFXButton btnRemove) {
        this.reservationID = reservationID;
        this.regNo = regNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysCount = daysCount;
        this.totalCost = totalCost;
        this.btnRemove = btnRemove;
    }

    public CartTM(String NIC, String cusName, String vehicleName, Double costPerDay) {
        this.NIC = NIC;
        this.cusName = cusName;
        this.vehicleName = vehicleName;
        this.costPerDay = costPerDay;
    }
}
