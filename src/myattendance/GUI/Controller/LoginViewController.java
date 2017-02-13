/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
<<<<<<< HEAD
=======
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
>>>>>>> Development
import javafx.stage.Stage;
import myattendance.GUI.Model.AttendanceParser;

/**
 * FXML Controller class
 *
 * @author Kristoffers
 */
public class LoginViewController implements Initializable
{

<<<<<<< HEAD
    /**
     * Gets the singleton instance of the model.
=======
    private final String studentUsername = "student";
    private final String teacherUsername = "teacher";
    private final String password = "pass";

    /**
     * Gets the singleton instance of AttendanceParser.java.
>>>>>>> Development
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();

    @FXML
    private Button loginButton;
<<<<<<< HEAD
=======
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox rememberMeCheckBox;
>>>>>>> Development

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException
    {
<<<<<<< HEAD
        attendanceParser.changeView("Homepage", "GUI/View/MainAttendanceOverview.fxml");

        // Closes the primary stage
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
=======
        if (usernameField.getText().equals(studentUsername) && passwordField.getText().equals("pass"))
        {
            attendanceParser.changeView("Homepage", "GUI/View/MainAttendanceOverview.fxml");

            // Closes the primary stage
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
        if (usernameField.getText().equals(teacherUsername) && passwordField.getText().equals("pass"))
        {
            attendanceParser.changeView("Homepage", "GUI/View/TeacherHomepage.fxml");

            // Closes the primary stage
            Stage stage = (Stage) loginButton.getScene().getWindow();
        }

>>>>>>> Development
    }

}
