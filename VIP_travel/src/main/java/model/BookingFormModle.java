package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data


public class BookingFormModle {

    private ReservationModle reservation;
    private List<BookingDetailsModle> BookingList;

}
