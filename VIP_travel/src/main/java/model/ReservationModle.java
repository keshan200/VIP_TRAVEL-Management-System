package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationModle {

    private String ReserstionID;
    private String NIC;


    public ReservationModle(String reserstionID) {
        ReserstionID = reserstionID;
    }
}
