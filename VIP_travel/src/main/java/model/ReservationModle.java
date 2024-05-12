package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationModle {

    private String ReserstionID;
    private String NIC;
    private LocalDate  reservationDate;


    public ReservationModle(String reserstionID) {
        ReserstionID = reserstionID;
    }


}
