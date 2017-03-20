/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Controller;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    Student lastSelectedStudent;

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    PieChart absenceChart = new PieChart(pieChartData);

    Label absenceLabel = new Label();

    private Button absenceOverviewButton;
    @FXML
    private TextField txtFldSearchStudent;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> cBoxClassSelection;
    private Button btnLogOut;
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
    @FXML
    private VBox vBoxMiddle;
    @FXML
    private Button btnAbsenceOverview;
    @FXML
    private Button btnLogout;
    @FXML
    private Pagination paginationBtn;

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

        absenceChart.setTitle("Student Absence");
        paginationBtn.setVisible(false);
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Login", "GUI/View/LoginView.fxml", null);

        // Closes the primary stage
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAbsenceOverview(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Absence Overview", "GUI/View/StatisticAttendanceOverview.fxml", null);

        // Closes the primary stage
        Stage stage = (Stage) btnAbsenceOverview.getScene().getWindow();
        stage.close();
    }

    private void fillComboBox()
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList("Select Class", "CS2016A", "CS2016B");
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
            if (cBoxClassSelection.getValue().equals("CS2016A"))
            {
                ObservableList<Student> studentList = FXCollections.observableArrayList(studentParser.getDanishClassList());

                tblStatusView.setItems(studentList);

            } else if (cBoxClassSelection.getValue().equals("CS2016B"))
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
            if (cBoxClassSelection.getValue().equals("CS2016A"))
            {
                unFilteredList = studentParser.getDanishClassList();

                for (Student s : unFilteredList)
                {
                    if (s.getName().toLowerCase().contains(txtFldSearchStudent.getText().toLowerCase()))
                    {
                        filteredList.add(s);
                    }
                }

                ObservableList<Student> studentList = FXCollections.observableArrayList(filteredList);
                tblStatusView.setItems(studentList);

            } else if (cBoxClassSelection.getValue().equals("CS2016B"))
            {
                unFilteredList = studentParser.getInternationalClassList();

                for (Student s : unFilteredList)
                {
                    if (s.getName().toLowerCase().contains(txtFldSearchStudent.getText().toLowerCase()))
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
        txtFldSearchStudent.requestFocus();

    }

    @FXML
    private void keyReleaseSearchField(KeyEvent event)
    {
        populateOnlineList();
    }

    @FXML
    private void clickStatistics(ActionEvent event)
    {

        vBoxMiddle.getChildren().clear();
        
        if(tblStatusView.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a class and then point on a student inside the studentlist");

            alert.showAndWait();
            
            paginationBtn.setVisible(false);
        }
        else
        {

        vBoxMiddle.getChildren().add(absenceChart);
        vBoxMiddle.getChildren().add(absenceLabel);
        vBoxMiddle.setAlignment(Pos.CENTER);
        paginationBtn.setVisible(true);
        }
    }

    @FXML
    private void clickTableStudents(MouseEvent event)
    {
        if (!tblStatusView.getItems().isEmpty())
        {
            lastSelectedStudent = tblStatusView.getSelectionModel().getSelectedItem();
            pieChartData.clear();
            pieChartData.add(new PieChart.Data("Absence", lastSelectedStudent.getAbsentClasses()));
            pieChartData.add(new PieChart.Data("Presence", lastSelectedStudent.getPresentClasses()));
            absenceLabel.setText(
                    "Student Attendance: "
                    + lastSelectedStudent.getPresentClasses()
                    + "/"
                    + Math.addExact(lastSelectedStudent.getAbsentClasses(), lastSelectedStudent.getPresentClasses()));
            
        }
    }

}
