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
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myattendance.GUI.Model.AttendanceParser;
import myattendance.GUI.Model.StudentParser;

/**
 * FXML Controller class
 *
 * @author Fjord82
 */
public class StudentMainOverviewController implements Initializable
{

    @FXML
    private VBox vBoxSelectionContent;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnAbsenceOverview;
    @FXML
    private Button btnLogout;

    AttendanceParser attendanceParser = AttendanceParser.getInstance();
    StudentParser studentParser = StudentParser.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleAbsenceOverview(ActionEvent event)
    {
    }

    @FXML
    private void clickStatistics(ActionEvent event)
    {
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Login", "GUI/View/LoginView.fxml");

        // Closes the primary stage
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

}
