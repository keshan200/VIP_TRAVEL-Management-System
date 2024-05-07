package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnDetailsModle {

    private String returnID;// Add vehicleID field
    private String regNo;


    public ReturnDetailsModle(String returnID, String status, LocalDate returnDate, String nic, String damages, String description, String regNo) {
    }
}
