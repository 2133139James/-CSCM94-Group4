package cscm12.cafe94;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * [Orders]
 * Class for receiving and displaying orders.
 * @author Patrick Rose
 * @version 1.0
 */
public class Orders {
    private SimpleStringProperty main;
    private SimpleStringProperty side;
    private SimpleStringProperty drink;
    private SimpleStringProperty orderType;
    private SimpleIntegerProperty referenceNumber;

    public Orders(String main, String side, String drink, String orderType, int referenceNumber) {
        this.main = new SimpleStringProperty(main);
        this.side = new SimpleStringProperty(side);
        this.drink = new SimpleStringProperty(drink);
        this.orderType = new SimpleStringProperty(orderType);
        this.referenceNumber = new SimpleIntegerProperty(referenceNumber);
    }

    @FXML
    private TableView<Orders> outstandingOrders;
    @FXML
    private TableColumn<Orders, Number> fieldOrderRef;
    @FXML
    private TableColumn<Orders, String> fieldMain;
    @FXML
    private TableColumn<Orders, String> fieldSide;
    @FXML
    private TableColumn<Orders, String> fieldDrink;
    @FXML
    private TableColumn<Orders, String> fieldOrderType;

    /**
     * [showIncomplete]
     * Gets all orders that are yet to be completed to display to the chef.
     */
    public ObservableList<Orders> getOutstanding() {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        ObservableList<Orders> outstandingOrders =  FXCollections.observableArrayList();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM vKitchenTickets WHERE IsCompleted = '0'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            Orders orders;
            while (resultSet.next()){
                orders = new Orders(resultSet.getString("Main"),
                        resultSet.getString("Side"),
                        resultSet.getString("Drink"),
                        resultSet.getString("order_type"),
                        resultSet.getInt("reference_number"));
                outstandingOrders.add(orders);
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outstandingOrders;
    }
    /**
     * [getOutstandingTable]
     * This is a method is used to get the extracted SQL data.
     * This is for @switchToManageStaff.*/
    public void getOutstandingTable() {
        ObservableList<Orders> order = getOutstanding();
        try {
            fieldOrderRef.setCellValueFactory(cellData -> cellData.getValue().referenceNumberProperty());
            fieldMain.setCellValueFactory(cellData -> cellData.getValue().mainProperty());
            fieldSide.setCellValueFactory(cellData -> cellData.getValue().sideProperty());
            fieldDrink.setCellValueFactory(cellData -> cellData.getValue().drinkProperty());
            fieldOrderType.setCellValueFactory(cellData -> cellData.getValue().orderTypeProperty());
            outstandingOrders.setItems(order);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }
    /**
     * [orderTotal]
     * Method to return the total price of an order.
     * @param referenceNumber
     * @param orderType
     * @return total
     */
    public double getOrderTotal(int referenceNumber, String orderType) {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        double total = 0.0;
        try {
            String sql = "SELECT Total FROM vFinanceSheet WHERE reference_number = '"
                    + referenceNumber + "' AND order_type = '" + orderType + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            resultSet.next();
            total = resultSet.getDouble("Total");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public String getMain() {
        return main.get();
    }

    public void setMain(String aMain) {
        main.set(aMain);
    }

    public SimpleStringProperty mainProperty() {
        return main;
    }

    public String side() {
        return side.get();
    }

    public void setSide(String aSide) {
        side.set(aSide);
    }

    public SimpleStringProperty sideProperty() {
        return side;
    }

    public String drink() {
        return drink.get();
    }

    public void setDrink(String aDrink) {
        drink.set(aDrink);
    }

    public SimpleStringProperty drinkProperty() {
        return drink;
    }

    public String orderType() {
        return orderType.get();
    }

    public void setOrderType(String anOrderType) {
        orderType.set(anOrderType);
    }

    public SimpleStringProperty orderTypeProperty() {
        return orderType;
    }

    public int referenceNumber() {
        return referenceNumber.get();
    }

    public void setReferenceNumber(int aReferenceNumber) {
        referenceNumber.set(aReferenceNumber);
    }

    public SimpleIntegerProperty referenceNumberProperty() {
        return referenceNumber;
    }
}

