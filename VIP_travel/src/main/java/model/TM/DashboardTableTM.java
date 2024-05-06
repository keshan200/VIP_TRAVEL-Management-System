package model.TM;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DashboardTableTM {

    private String vehicleName;
    private int totalAvailable;
    private double costPerDay;
}
