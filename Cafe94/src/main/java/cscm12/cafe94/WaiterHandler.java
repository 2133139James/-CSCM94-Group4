package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
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
