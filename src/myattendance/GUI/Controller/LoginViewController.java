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
public class LoginViewController implements Initializable
{

    /**
     * Gets the singleton instance of the model.
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();

    @FXML
    private Button loginButton;

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
        attendanceParser.changeView("Homepage", "GUI/View/MainAttendanceOverview.fxml");

        // Closes the primary stage
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

}
