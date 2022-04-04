package cscm12.cafe94;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.Objects;

/**
 * [StartScreenController]
 * Holds methods for the Start Screen fxml.
 * @author Sumi Sunuwar, Ryan Noscoe
 * @version 1.1*/

public class StartScreenController {
    /**[Field Variables]
     * These are for setting up stages to be displayed in the application. */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView littleChefView;
    //private Image littleChef = new Image(getClass().getResourceAsStream("pngkit_ratatouille-png_9388574.png"));

    /**   [switchToStaffLogin]
     Switches to staffs login page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToStaffLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**   [switchToOrderType]
     Switches to page where customers select their order type.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToOrderType(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderType.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
