package myattendance.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myattendance.GUI.Model.AttendanceParser;
import myattendance.GUI.Model.IPParser;

public class LoginViewController implements Initializable {

    private final String studentUsername = "student";
    private final String teacherUsername = "teacher";
    private final String password = "pass";

    /**
     * Gets the singleton instance of AttendanceParser.java.
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();
    IPParser iPParser = IPParser.getInstance();

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox rememberMeCheckBox;
    @FXML
    private Label wrongLoginLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Hides the "Wrong username or password" label
        wrongLoginLabel.setVisible(false);
    }
       

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        
        //System.out.println(iPParser.getIsMatchingBroadcasting());
        
        if (usernameField.getText().equals(studentUsername) && passwordField.getText().equals(password)) {
            attendanceParser.changeView("Homepage", "GUI/View/StudentMainOverview.fxml");

            // Closes the primary stage
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
        if (usernameField.getText().equals(teacherUsername) && passwordField.getText().equals(password)) {
            attendanceParser.changeView("Homepage", "GUI/View/MainAttendanceOverview.fxml");

            // Closes the primary stage
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        } else {
            // Displays the "Wrong username or password" label
            wrongLoginLabel.setVisible(true);
            usernameField.requestFocus();
        }

    }

}
