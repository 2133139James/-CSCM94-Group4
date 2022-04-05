package cscm12.cafe94;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/** Container class for driver tickets.
 * @author Paul Norman
 * @version 1.0
 */
public class DriverTicket extends Ticket {

    private SimpleIntegerProperty orderID;
    private SimpleStringProperty deliveryAddress;
    private SimpleStringProperty driverName;

    public DriverTicket(int orderID, String main, String side, String drink,
                        String deliveryAddress, String driverName) {
        super(main, side, drink);
        this.orderID = new SimpleIntegerProperty(orderID);
        this.deliveryAddress = new SimpleStringProperty(deliveryAddress);
        this.driverName = new SimpleStringProperty(driverName);
    }

    public int getOrderID() {
        return orderID.get();
    }

    public SimpleIntegerProperty orderIDProperty() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID.set(orderID);
    }

    public String getDeliveryAddress() {
        return deliveryAddress.get();
    }

    public SimpleStringProperty deliveryAddressProperty() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress.set(deliveryAddress);
    }

    public String getDriverName() {
        return driverName.get();
    }

    public SimpleStringProperty driverNameProperty() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName.set(driverName);
    }


    /**
     * Sets the order as delivered in database.
     */
    public void deliver(){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE DeliveryOrders " +
                            "SET IsDelivered = 'true' " +
                            "WHERE DeliveryOrderID = " + orderID.intValue() + ";",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }

}
