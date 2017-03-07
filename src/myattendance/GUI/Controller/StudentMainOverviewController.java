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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myattendance.BE.Student;
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

    @FXML
    private VBox vBoxMiddle;

    AttendanceParser attendanceParser = AttendanceParser.getInstance();
    StudentParser studentParser = StudentParser.getInstance();

    Student rasmus = studentParser.getRasmus();
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblStudentClass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        showConstantCalender();
       
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.add(new PieChart.Data("Absence", rasmus.getAbsentClasses()));
        pieChartData.add(new PieChart.Data("Presence", rasmus.getPresentClasses()));
        
        PieChart absenceChart = new PieChart(pieChartData);
        absenceChart.setTitle("Absence");
        
        Label absenceLabel = new Label();
        absenceLabel.setText("Student Attendance: " + rasmus.getPresentClasses() + "/" + Math.addExact(rasmus.getAbsentClasses(), rasmus.getPresentClasses()));
        
        

        vBoxMiddle.getChildren().add(absenceChart);
        vBoxMiddle.getChildren().add(absenceLabel);
        
        
        lblStudentName.setText(rasmus.getName());
        lblStudentClass.setText("CS2016B");
    }


    @FXML
    private void handleLogout(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Login", "GUI/View/LoginView.fxml");

        // Closes the primary stage
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }
    
    private void showConstantCalender()
    {

        //Install JFxtra from the internet!!!
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));

        Node popupContent = datePickerSkin.getPopupContent();

        vBoxSelectionContent.setPadding(new Insets(10));

        vBoxSelectionContent.getChildren().add(popupContent);

    }

}
