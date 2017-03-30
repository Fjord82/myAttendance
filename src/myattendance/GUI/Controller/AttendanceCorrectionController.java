package myattendance.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import myattendance.GUI.Model.AttendanceParser;

public class AttendanceCorrectionController implements Initializable
{

    /**
     * Gets the singleton instance of AttendanceParser.java.
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();

    private Button overviewButton;
    @FXML
    private Label nameLbl;
    @FXML
    private Button btnBackToMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleHomepage(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Homepage", "GUI/View/TeacherAttendanceOverview.fxml", null);

        // Closes the primary stage
        Stage stage = (Stage) btnBackToMain.getScene().getWindow();
        stage.close();
    }


}
