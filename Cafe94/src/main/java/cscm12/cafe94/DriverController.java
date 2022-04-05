package cscm12.cafe94;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static cscm12.cafe94.StaffLoginController.staffSession;

public class DriverController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button deliverOrderButton;
    @FXML
    private TableView<DriverTicket> deliveryTickets;
    @FXML
    private TableColumn<DriverTicket, Number> orderID;
    @FXML
    private TableColumn<DriverTicket, String> deliveryAddress;
    @FXML
    private TableColumn<DriverTicket, String> main;
    @FXML
    private TableColumn<DriverTicket, String> side;
    @FXML
    private TableColumn<DriverTicket, String> drink;

    public void getTicketTable() {
        // factory design pattern
        ///
        DeliveryHandler handler = new DeliveryHandler();
        ObservableList<DriverTicket> tickets = handler.getOrdersForDriver(staffSession);
        try {
            orderID.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
            deliveryAddress.setCellValueFactory(cellData -> cellData.getValue().deliveryAddressProperty());
            main.setCellValueFactory(cellData -> cellData.getValue().getDrink());
            side.setCellValueFactory(cellData -> cellData.getValue().getDrink());
            drink.setCellValueFactory(cellData -> cellData.getValue().getSide());
            deliveryTickets.setItems(tickets);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }

    /** Sets delivery order to IsDelivered.
     * @param event
     */
    public void deliver(ActionEvent event){
        int index = deliveryTickets.getSelectionModel().getSelectedIndex();
        DriverTicket ticket = deliveryTickets.getItems().get(index);
        getTicketTable();
    }

    @FXML
    public void switchToAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Accounts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     Switches to staffs login page.
     @param event is to trigger fxml swap */
    @FXML
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
    }


}
