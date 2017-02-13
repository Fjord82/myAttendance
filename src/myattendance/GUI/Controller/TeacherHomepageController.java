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
import javafx.stage.Stage;
import myattendance.GUI.Model.AttendanceParser;

/**
 * FXML Controller class
 *
 * @author Kristoffers
 */
public class TeacherHomepageController implements Initializable
{

    /**
     * Gets the singleton instance of AttendanceParser.java.
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();

    @FXML
    private Button logoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Login", "GUI/View/LoginView.fxml");

        // Closes the primary stage
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

}
