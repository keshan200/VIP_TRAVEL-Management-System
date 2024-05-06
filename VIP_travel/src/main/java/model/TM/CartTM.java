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


}
