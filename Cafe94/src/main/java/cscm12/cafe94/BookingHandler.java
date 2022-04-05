package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;


/**
 * For booking related database calls
 * @author Paul Norman
 * @version 1.0
 */
public class BookingHandler extends DatabaseHandler {

    /**
     * Returns all bookings which have not been approved yet for the @WaiterController.java
     * @return Observable list of unapproved bookings.
     */
    public ObservableList<Booking> getUnapprovedBookings(){
        ObservableList<Booking> bookingList = FXCollections.observableArrayList();
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        try {
            String query = "SELECT BookingID, CustomerID, NumberGuests, BookingTime," +
                    "TableID FROM [dbo].[BookingTables]" +
                    "WHERE IsApproved = 0;";
            PreparedStatement checkDatabase = connect.prepareStatement(query);
            ResultSet resultSet = checkDatabase.executeQuery();
            Booking booking = null;

            while (resultSet.next()) {
                booking = new Booking(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(5),
                        resultSet.getTimestamp(4).toLocalDateTime());
                bookingList.add(booking);
            }
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingList;


    }



}
