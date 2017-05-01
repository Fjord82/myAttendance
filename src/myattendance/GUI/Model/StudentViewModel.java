package myattendance.GUI.Model;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;

/**
 *
 * @author meng
 */
public class StudentViewModel

{

    BLLFacade bllFacade = BLLFacade.getInstance();

    public void updateLastLogin(User user)
    {
        bllFacade.updateLastLogin(user);
    }

    public ObservableList<PieChart.Data> getPieChartData(User user)
    {
        return bllFacade.getPieChartData(user);
    }

    public XYChart.Series<String, Number> getStackedChartData(User user)
    {
        return bllFacade.getStackedChartData(user);
    }

    public XYChart.Series<String, Number> getLineChartData(User user)
    {
        return bllFacade.getLineChartData(user);
    }



    public List<Day> getDaysUptoToday()
    {
        return bllFacade.getDaysUptoToday();
    }

}
