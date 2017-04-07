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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import myattendance.BE.User;
import myattendance.GUI.Model.AttendanceParser;

public class LoginViewController implements Initializable
{

    User student = new User();

    /**
     * Gets the singleton instance of AttendanceParser.java.
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
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

            attendanceParser.tryLogin(usernameField.getText(), passwordField.getText(), stage);

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
        if (attendanceParser.establishServerConnection()==true)
        {
            LabelConnection.setTextFill(Color.GREEN);
            LabelConnection.setText("Connected to school network");
            loginButton.setDisable(false);

        } else
        {
            LabelConnection.setTextFill(Color.RED);
            LabelConnection.setText("Wrong network connected");
            loginButton.setDisable(true);

        }
    }

}
