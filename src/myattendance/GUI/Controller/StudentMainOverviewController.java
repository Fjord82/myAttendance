/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Fjord82
 */
public class StudentMainOverviewController implements Initializable
{

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuAttendanceList;
    @FXML
    private Menu menuStatistics;
    @FXML
    private Menu menuHelp;
    @FXML
    private VBox vBoxSelectionContent;
    @FXML
    private ComboBox<?> cBoxClassSelection;
    @FXML
    private DatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickCBox(ActionEvent event)
    {
    }
    
}
