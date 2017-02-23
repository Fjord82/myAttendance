/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Controller;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import myattendance.BE.Student;
import myattendance.GUI.Model.AttendanceParser;
import myattendance.GUI.Model.StudentParser;

/**
 * FXML Controller class
 *
 * @author Kristoffers
 */
public class MainAttendanceOverviewController implements Initializable
{

    /**
     * Gets the singleton instance of AttendanceParser.java.
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();
    StudentParser studentParser = StudentParser.getInstance();

    private Button absenceOverviewButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuAttendanceList;
    @FXML
    private Menu menuStatistics;
    @FXML
    private Menu menuHelp;
    @FXML
    private TextField txtFldSearchStudent;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> cBoxClassSelection;
    private Button btnLogOut;
    @FXML
    private StackPane stackPane;
    @FXML
    private VBox vBoxSelectionContent;

    @FXML
    private Label labelPresentCounter;

    Student student;
    @FXML
    private TableColumn<Student, String> tblViewName;
    @FXML
    private TableColumn<Student, String> tblViewStatus;
    @FXML
    private TableView<Student> tblStatusView;
    @FXML
    private VBox vBoxStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fillComboBox();

        showConstantCalender();
        populateOnlineList();
        updatePresentCounter();
    }

    private void handleLogout(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Login", "GUI/View/LoginView.fxml");

        // Closes the primary stage
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();
    }

    private void handleAbsenceOverview(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Absence Overview", "GUI/View/StatisticAttendanceOverview.fxml");

        // Closes the primary stage
        Stage stage = (Stage) absenceOverviewButton.getScene().getWindow();
        stage.close();
    }

    private void fillComboBox()
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList("Select Class", "CS DK 2.Sem", "CS INT 2.Sem");
        cBoxClassSelection.setItems(comboItems);
        cBoxClassSelection.getSelectionModel().selectFirst();
    }

    private void showConstantCalender()
    {

        //Install JFxtra from the internet!!!
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));

        Node popupContent = datePickerSkin.getPopupContent();

        vBoxSelectionContent.setPadding(new Insets(10));

        vBoxSelectionContent.getChildren().add(popupContent);

    }

    private void populateOnlineList()
    {
        vBoxStatus.setPadding(new Insets(10));

        if (txtFldSearchStudent.getText().equals(""))
        {
            if (cBoxClassSelection.getValue().equals("CS DK 2.Sem"))
            {
                ObservableList<Student> studentList = FXCollections.observableArrayList(studentParser.getDanishClassList());

                tblStatusView.setItems(studentList);

            } else if (cBoxClassSelection.getValue().equals("CS INT 2.Sem"))
            {
                ObservableList<Student> studentList = FXCollections.observableArrayList(studentParser.getInternationalClassList());
                tblStatusView.setItems(studentList);

            } else if (cBoxClassSelection.getValue().equals("Select Class"))
            {

                tblStatusView.getItems().clear();
            }
        } else
        {
            List<Student> filteredList = new ArrayList<>();
            List<Student> unFilteredList = new ArrayList<>();
            if (cBoxClassSelection.getValue().equals("CS DK 2.Sem"))
            {
                unFilteredList = studentParser.getDanishClassList();

                for (Student s : unFilteredList)
                {
                    if (s.getName().contains(txtFldSearchStudent.getText()))
                    {
                        filteredList.add(s);
                    }
                }

                ObservableList<Student> studentList = FXCollections.observableArrayList(filteredList);
                tblStatusView.setItems(studentList);

            } else if (cBoxClassSelection.getValue().equals("CS INT 2.Sem"))
            {
                unFilteredList = studentParser.getInternationalClassList();

                for (Student s : unFilteredList)
                {
                    if (s.getName().contains(txtFldSearchStudent.getText()))
                    {
                        filteredList.add(s);
                    }
                }

                ObservableList<Student> studentList = FXCollections.observableArrayList(filteredList);
                tblStatusView.setItems(studentList);

            } else if (cBoxClassSelection.getValue().equals("Select Class"))
            {

                tblStatusView.getItems().clear();
            }
        }
        tblViewName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tblViewStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        updatePresentCounter();
    }

    private void sortStudentStatus()
    {

    }

    /**
     * Updates the counter for how many students are currently present, out of
     * the total amount.
     *
     * Max present is the total number of students in the table. Currently
     * present is the amount of students with the status 'Online'.
     */
    private void updatePresentCounter()
    {
        String labelText = "";
        int maxPresent = tblStatusView.getItems().size();
        int currentlyPresent = 0;

        for (Student s : tblStatusView.getItems())
        {
            if (s.getStatus().equals("Online"))
            {
                currentlyPresent++;
            }
        }

        labelText = "Currently Present: " + currentlyPresent + "/" + maxPresent;
        labelPresentCounter.setText(labelText);
    }

    @FXML
    private void clickCBox(ActionEvent event)
    {
        populateOnlineList();
        txtFldSearchStudent.clear();

    }

    @FXML
    private void keyReleaseSearchField(KeyEvent event)
    {
        populateOnlineList();
    }

}
