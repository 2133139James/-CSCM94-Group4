package cscm12.cafe94;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import static cscm12.cafe94.StaffLoginController.staffSession;

/**[StaffHomepage]
 * Class which handles staffs custom homepage with functions based on their role.
 * @author Sumi Sunuwar
 * @version 1.1 */
public class StaffHomepageController implements Initializable {

    /**[Field Variables]
     * These are for setting up stages to be displayed in the application. */
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**[TextField]
     * Text boxes to fill appropriate information to edit, delete or add staff. */
    @FXML
    private Label staffType;
    @FXML
    private Label staffFName;
    @FXML
    private Label staffLName;
    @FXML
    private Label staffHoursToWork;
    @FXML
    private Label staffHoursToWorkDay;

    /**[getStaffHomeInfo]
     This method is used to get the staff homepage information.
     This includes the staffs first name, surname, hours and the staff type. */
    public void getStaffHomeInfo() {
        DatabaseHandler staffDatabase = new DatabaseHandler();
        Connection connect = staffDatabase.database();

        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM Staff WHERE StaffUsername='" + staffSession + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();

            while (resultSet.next()) {
                staffType.setText(resultSet.getString("StaffType"));
                staffFName.setText(resultSet.getString("StaffFirst_Name"));
                staffLName.setText(resultSet.getString("StaffLast_Name"));
                staffHoursToWork.setText(String.valueOf(resultSet.getInt("HoursToWork")));
                int hoursToWorkWeek = resultSet.getInt("HoursToWork");
                staffHoursToWorkDay.setText(String.valueOf(hoursToWorkWeek / 5));
            }
            statement.close();
            connect.close();
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**   [switchToStaffLogin]
     Switches to staffs login page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToStaffLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Blue.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**  [switchToManager]
     Switches to managers page.
     This page gives access to @switchToManager and @switchToManageStaff.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManageStaff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Blue.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**  [switchToManageStaff]
     Switches to manage staff page.
     This page gives access to @getStaffTable, @newStaff, @editStaff and @deleteStaff.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToManageStaff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManageStaff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Blue.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Accounts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**[initialize]
     Switches to Managers page.
     @param location location of the method.
     @param resources resources of the method. */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getStaffHomeInfo();
    }
}
