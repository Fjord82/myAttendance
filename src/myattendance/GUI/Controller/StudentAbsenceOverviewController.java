/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import myattendance.BE.Day;
import myattendance.BE.User;

/**
 * FXML Controller class
 *
 * @author Fjord82
 */
public class StudentAbsenceOverviewController implements Initializable
{

    @FXML
    private Label nameLbl;
    @FXML
    private TableView<Day> tblAbsenceOverview;
    @FXML
    private TableColumn<Day, String> columnAbsenceDays;
    @FXML
    private Button btnBackToMain;
    
    User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnAbsenceDays.setCellValueFactory(cellData -> cellData.getValue().toStringProperty());
    }  
    
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
    private void handleHomepage(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) btnBackToMain.getScene().getWindow();
        stage.close();
    }
    
}
