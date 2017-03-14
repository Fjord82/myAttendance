package myattendance.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import myattendance.BE.Student;

/**
 *
 * @author jeppe
 */
public class DatabaseAccess
{

    private static SQLServerDataSource ds = new SQLServerDataSource();

    private static DatabaseAccess instance;

    public static DatabaseAccess getInstance()
    {
        if (instance == null)
        {
            instance = new DatabaseAccess();
        }
        return instance;
    }

    private DatabaseAccess()
    {
        setupDataSource();
        //printStudents();
    }

    private static void setupDataSource()
    {
        ds.setDatabaseName("CS2016B_12_Attendance");
        ds.setUser("CS2016B_12");
        ds.setPassword("CS2016B_12");

        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }

    private static void printStudents()
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM Students WHERE classid = 2";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                System.out.print(rs.getString("sid") + " ");
                System.out.print(rs.getString("fname") + " ");
                System.out.print(rs.getString("mname") + " ");
                System.out.print(rs.getString("lname") + " ");
                System.out.print(rs.getString("classid") + " ");
                System.out.println("");
            }

        } catch (SQLException sqle)
        {
            System.err.println(sqle);
        }
    }

    public static Student getStudent(String login, String pass)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("USE CS2016B_12_Attendance GO SELECT * FROM Students WHERE slog =? AND spass =?");
            System.out.println("Creating query");
            ps.setString(1, login);
            ps.setString(2, pass);
            System.out.println("Sending query");
            ResultSet rs; 
            rs = ps.executeQuery();
            System.out.println(rs.getRow());
            System.out.println(rs.getString("fname"));
            /*String query = "SELECT * FROM Students WHERE slog = '" + login + "' AND spass = '" + pass + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
*/

            String fullName = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname");
            Student student = new Student(fullName);
            System.out.println(student.getName());

            return student;

        } catch (SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }

    }
}
