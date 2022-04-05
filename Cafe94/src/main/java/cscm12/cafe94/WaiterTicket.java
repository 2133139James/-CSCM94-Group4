package cscm12.cafe94;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Object for holding waiter food tickets.
 * @author Paul Norman
 * @version 1.0
 */
public class WaiterTicket extends Ticket {

    private SimpleIntegerProperty tableNumber;
    private int ref;

    public WaiterTicket(int tableNumber, String main, String side, String drink, int ref) {
        super(main, side, drink);
        this.tableNumber = new SimpleIntegerProperty(tableNumber);
        this.ref = ref;
    }

    public SimpleIntegerProperty getTableNumber(){
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber.set(tableNumber);
    }

    public void serve(){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE SitDownOrders " +
                            "SET IsServed = 'true' " +
                            "WHERE SitDownOrderID = " + ref + ";",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }


    /**
     * Sets as collected if a takeaway.
     */
    public void collect(){ // workaround to avoid another class that does the same thing.
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE TakeawayOrders " +
                            "SET IsCollected = 'true' " +
                            "WHERE TakeawayOrderID = " + tableNumber.intValue() + ";",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }
}
