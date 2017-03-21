package myattendance.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myattendance.BE.User;

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
            String query = "SELECT * FROM People WHERE slog=?";
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

    public User loginQuery(String login, String pass)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT People.fname, People.mname, People.lname, People.Teacher, Classes.ClassName FROM People, Classes, ClassRelation WHERE People.PID = ClassRelation.PID AND Classes.ClassID = ClassRelation.ClassID AND People.slog=? AND People.spass=?");
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            System.out.println("Loop start");
            rs.next();
            
                System.out.println("Loop Started");
                User user;
                boolean isTeacher = rs.getBoolean("Teacher");
                String fullName = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname");
                System.out.println("Conditions start");

                if (!isTeacher)
                {

                    String className = rs.getString("classname");

                    user = new User(fullName, className, isTeacher);
                } else
                {

                    user = new User(fullName, isTeacher);
                }
                return user;
            

        } catch (SQLException ex)
        {
            System.out.println(ex);
            System.out.println("Connection Error");
            return null;
        }
    }
}
