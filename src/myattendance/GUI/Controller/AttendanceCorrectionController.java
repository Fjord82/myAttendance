package myattendance.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.GUI.Model.AttendanceParser;
import org.joda.time.DateTime;

/**
 *
 * @author Kristoffers
 */
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
    @FXML
    private Button removeBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TableView<Day> tblAbsenceOverview;
    @FXML
    private Label confirmLbl;
    @FXML
    private TableColumn<Day, String> columnAbsenceDays;

    private User user = new User();
    @FXML
    private DatePicker dpCalendar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        columnAbsenceDays.setCellValueFactory(cellData -> cellData.getValue().toStringProperty());

    }

    /**
     *
     * @param user
     */
    public void setUser(User user)
    {
        this.user = user;
        nameLbl.setText(user.getName());
        populateList();

    }

    /**
     *
     */
    public void populateList()
    {
        //ObservableList<Day> absenceList = FXCollections.observableArrayList(user.getAbsentDays());

        tblAbsenceOverview.setItems(FXCollections.observableArrayList(user.getAbsentDays()));

    }

    @FXML
    private void handleHomepage(ActionEvent event) throws IOException
    {
        //attendanceParser.changeView("Homepage", "GUI/View/TeacherAttendanceOverview.fxml", null);

        // Closes the primary stage
        Stage stage = (Stage) btnBackToMain.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleRemoveContent(ActionEvent event)
    {
        Day selectedDay = tblAbsenceOverview.getSelectionModel().getSelectedItem();

        if (selectedDay == null)
        {
            confirmLbl.setText("Select a day from above list to remove!");
        } else
        {

            attendanceParser.deleteAbsenceFromDB(user, selectedDay);
            user.getAbsentDays().remove(selectedDay);
            populateList();

//            Button yesBtn = new Button("Yes");
//            Button noBtn = new Button("No");
//            confirmLbl.setText("Are you sure you want to remove selected from list? ");  
        }

    }

    @FXML
    private void handleAddContent(ActionEvent event)
    {
//        LocalDate lDate = dpCalendar.getValue();
//        attendanceParser.writeAbsencesIntoDB(user, lDate);
    }
}
