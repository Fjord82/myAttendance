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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myattendance.BE.Student;
import myattendance.GUI.Model.AttendanceParser;

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

    private Button absenceOverviewButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuWeekSchedule;
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
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnAttendanceReqst;
    @FXML
    private StackPane stackPane;
    @FXML
    private VBox vBoxSelectionContent;
    @FXML
    private FlowPane flowPaneOnline;
    
    Student student;
    @FXML
    private TableColumn<Student, String> tblViewName;
    @FXML
    private TableColumn<Student, String> tblViewStatus;
    @FXML
    private TableView<Student> tblStatusView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fillComboBox();
        showConstantCalender();
        populateOnlineList(flowPaneOnline);
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
                = FXCollections.observableArrayList("Select Class", "CS DK 2.Sem", "CS INT 2.Sem", "CS DK 4.Sem");
        cBoxClassSelection.setItems(comboItems);
        cBoxClassSelection.getSelectionModel().selectFirst();
    }
    
    private void showConstantCalender()
    {
        
        //Install JFxtra from the internet!!!
        
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
        
            Node popupContent = datePickerSkin.getPopupContent();
            
            
            vBoxSelectionContent.getChildren().add(popupContent);
                   
    }
    
    private void populateOnlineList(FlowPane flowPaneOnline)
    {
        flowPaneOnline.setOrientation(Orientation.VERTICAL);
        flowPaneOnline.setPadding(new Insets(5));
        
        Student[] students = new Student[]{
            new Student ("Joe", "Online"),
            new Student ("Mark", "Online"),
            new Student ("Thomas", "Offline"),
            new Student ("Rocky", "Online"),
            new Student ("Ken", "Offline"),
            new Student ("Jimmy", "Offline"),
            new Student ("Clark", "Offline"),
            new Student ("Tommy", "Offline"),
            new Student ("Mick", "Offline"),
            new Student ("Chris", "Offline"),
            };
        
        ObservableList<Student> studentList = FXCollections.observableArrayList(students);
        tblViewName.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        tblStatusView.setItems(studentList);
        
        tblViewStatus.setCellValueFactory(cellData->cellData.getValue().statusProperty());
        
        
        /*for (int i=0; i< students.length; i++) 
        {
            flowPaneOnline.getChildren().add(new Label(students[i].Name() + students[i].Status()));
        }*/

    }
    
   /* private void loadStudentsIntoViewer()
    {
        try
        {
            ObservableList<Student> studentList = FXCollections.observableArrayList(student.getAllStudents());
            return studentList;
        } catch (IOException ex)
        {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/
    
    private void sortStudentStatus()
    {
        
    }

}
