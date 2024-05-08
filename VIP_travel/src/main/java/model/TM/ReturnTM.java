package model.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Data


public class ReturnTM {
    private String returnID;
    private String status;
    private LocalDate returnDate;
    private String NIC;
    private  String regNo;
    private String damages;
    private String desc;
    private  JFXButton remove;


    public ReturnTM(String returnID, String status, LocalDate rDate, String nic, String regNo, String damages, String desc) {
    }
}
