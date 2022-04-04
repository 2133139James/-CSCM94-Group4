package cscm12.cafe94;

import javafx.beans.property.SimpleIntegerProperty;

public class WaiterTicket extends Ticket {

    private SimpleIntegerProperty tableNumber;

    public WaiterTicket(int tableNumber, String main, String side, String drink) {
        super(main, side, drink);
        this.tableNumber = new SimpleIntegerProperty(tableNumber);

    }

    public SimpleIntegerProperty getTableNumber(){
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber.set(tableNumber);
    }

    public void markSitInOrderServed(){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE SitDownOrders " +
                            "SET IsServed = 'true' " +
                            "WHERE SitDownOrderID = " + tableNumber + ";",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }
}
