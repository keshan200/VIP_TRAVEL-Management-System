package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.BookingFormModle;

import java.sql.Connection;
import java.sql.SQLException;


/*public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.save(po.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = ItemRepo.update(po.getOdList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = OrderDetailRepo.save(po.getOdList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}*/
public class BookingFormRepo {
    public static boolean placeBooking(BookingFormModle b) throws SQLException {
        Connection connection = DBconnection.getInstance().getConnection();
        connection.setAutoCommit(false);




        try {
            boolean isBookingSaved = ReservationRepo.Save(b.getReservation());
            if (isBookingSaved) {

                boolean isBookingDetailSaved = BookingDetailsRepo.save(b.getBookingList());
                if (isBookingDetailSaved) {
                    connection.commit();
                    return true;
                }
            }

            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }



}
