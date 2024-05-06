package model.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class VehicleTM {

    private  String vehicleID;
    private String regNo;
    private String  year;
    private String  vehicleName;
    private String  fuelType;
    private String   vehicleType;
    private double   cost;
    private String    availability;


}
