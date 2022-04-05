package cscm12.cafe94;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Controller class for Waiter.fxml.
 * @author Paul Norman
 * @version 1.0
 */
public class WaiterController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button serveOrderButton;
    @FXML
    private Button bookTableButton;
    @FXML
    private TableView<WaiterTicket> waiterTickets;
    @FXML
    private TableColumn<WaiterTicket, Number> tableToServe;
    @FXML
    private TableColumn<WaiterTicket, String> mainToServe;
    @FXML
    private TableColumn<WaiterTicket, String> sideToServe;
    @FXML
    private TableColumn<WaiterTicket, String> drinkToServe;
    @FXML
    private TableView<Booking> approveBookings;
    @FXML
    private TableColumn<Booking, String> requestedDate;
    @FXML
    private TableColumn<Booking, Number> table;
    @FXML
    private TableColumn<Booking, Number> numberOfPeople;

    public void getTicketTable() {
        // factory design pattern
        ///
        WaiterHandler handler = new WaiterHandler();
        ObservableList<WaiterTicket> tickets = handler.getTickets();
        try {
            tableToServe.setCellValueFactory(cellData -> cellData.getValue().getTableNumber());
            mainToServe.setCellValueFactory(cellData -> cellData.getValue().getMain());
            sideToServe.setCellValueFactory(cellData -> cellData.getValue().getDrink());
            drinkToServe.setCellValueFactory(cellData -> cellData.getValue().getSide());
            waiterTickets.setItems(tickets);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }

    public void getUnapprovedBookings() {
        BookingHandler handler = new BookingHandler();
        ObservableList<Booking> bookings = handler.getUnapprovedBookings();
        try {
            table.setCellValueFactory(cellData -> cellData.getValue().simpleTableID());
            requestedDate.setCellValueFactory(cellData -> cellData.getValue().simpleDateTime());
            numberOfPeople.setCellValueFactory(cellData -> cellData.getValue().simpleNumberGuests());
            approveBookings.setItems(bookings);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }

    public void serve(ActionEvent e){
        int index = waiterTickets.getSelectionModel().getSelectedIndex();
        WaiterTicket ticket = waiterTickets.getItems().get(index);
        getTicketTable();
    }

    public void book(ActionEvent e) {
        int index = approveBookings.getSelectionModel().getSelectedIndex();
        Booking booking = approveBookings.getItems().get(index);
        booking.approveBooking();
        getUnapprovedBookings();
    }

    /*
     public void markAsComplete() {
         int index = outstandingOrders.getSelectionModel().getSelectedIndex();
         int referenceNum;
         String theOrderType;
         Orders order = outstandingOrders.getItems().get(index);
         referenceNum = order.referenceNumber();
         theOrderType = order.orderTypes();
         KitchenHandler handler = new KitchenHandler();
         handler.finishCook(referenceNum,theOrderType);
         getOutstandingTable();
     }

     */


    public void switchToAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Account.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     Switches to staff login page.
     @param event is to trigger fxml swap */
    public void switchToStaffLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTicketTable();
        getUnapprovedBookings();
    }




}
