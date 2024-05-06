package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnModle {


    private  String ReturnID;
    private LocalDate date;
    private String regNo;
    private String NIC;
    private String cusName;
    private String states;
    private String damge;
    private String desc;

    public ReturnModle(String returnID, LocalDate date, String nic, String states, String damge, String desc) {
    }

    public ReturnModle(String returnID, String status, LocalDate returnDate, String nic, String damages, String description) {
    }
}
