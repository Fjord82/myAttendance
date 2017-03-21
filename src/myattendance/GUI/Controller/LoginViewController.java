package myattendance.GUI.Controller;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import myattendance.BE.Student;
import myattendance.DAL.DatabaseAccess;
import myattendance.GUI.Model.AttendanceParser;
import myattendance.GUI.Model.IPParser;

public class LoginViewController implements Initializable
{

    Student student = new Student();

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
    @FXML
    private Label LabelConnection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        // Hides the "Wrong username or password" label
        wrongLoginLabel.setVisible(false);

        checkConnection();
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException
    {
        checkConnection();

        if (!usernameField.getText().isEmpty())
        {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            attendanceParser.getStudent(usernameField.getText(), passwordField.getText(), stage);

            // Closes the primary stage
        } else
        {
            // Displays the "Wrong username or password" label
            wrongLoginLabel.setVisible(true);
            usernameField.requestFocus();
        }

    }

    private void checkConnection()
    {
        if (iPParser.MatchingBroadcasting())
        {
            LabelConnection.setTextFill(Color.GREEN);
            LabelConnection.setText("Connected to school network");

        } else
        {
            LabelConnection.setTextFill(Color.RED);
            LabelConnection.setText("Wrong network connected");

        }
    }

}
