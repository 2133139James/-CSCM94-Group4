package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Handles Waiter.fxml. Contains methods for serving food and approving bookings.
 */
public class WaiterHandler extends DatabaseHandler {

    public ObservableList<WaiterTicket> getTickets() {
        ObservableList<WaiterTicket> ticketList = FXCollections.observableArrayList();
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        try {
            String query = "SELECT * from vWaiterTicket";
            PreparedStatement checkDatabase = connect.prepareStatement(query);
            ResultSet resultSet = checkDatabase.executeQuery();
            WaiterTicket ticket = null;

            while (resultSet.next()) {
                ticket = new WaiterTicket(resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        resultSet.getInt(6));
                ticketList.add(ticket);
            }
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    public ObservableList<WaiterTicket> getCollections() {
        ObservableList<WaiterTicket> ticketList = FXCollections.observableArrayList();
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        try {
            String query = "SELECT * from vCollectionTicket";
            PreparedStatement checkDatabase = connect.prepareStatement(query);
            ResultSet resultSet = checkDatabase.executeQuery();
            WaiterTicket ticket = null;

            while (resultSet.next()) {
                ticket = new WaiterTicket(resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        0);
                ticketList.add(ticket);
            }
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketList;
    }

}
