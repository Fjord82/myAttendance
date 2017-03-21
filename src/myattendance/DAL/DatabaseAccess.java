package myattendance.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import myattendance.BE.Student;

/**
 *
 * @author jeppe
 */
public class DatabaseAccess
{

    private static SQLServerDataSource ds = new SQLServerDataSource();

    public DatabaseAccess()
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
            String query = "SELECT * FROM Students WHERE slog=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "S1");
            ResultSet rs = ps.executeQuery();

            rs.next();
            System.out.print(rs.getString("sid") + " ");
            System.out.print(rs.getString("fname") + " ");
            System.out.print(rs.getString("mname") + " ");
            System.out.print(rs.getString("lname") + " ");
            System.out.print(rs.getString("classid") + " ");
            System.out.println("");

        } catch (SQLException sqle)
        {
            System.err.println(sqle);
        }
    }

    public static Student getStudent(String login, String pass)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT fname, mname, lname FROM Students WHERE slog =? AND spass =?");
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            rs.next();

            String fullName = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname");

            Student student = new Student(fullName);

            return student;

        } catch (SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }

    }


}
