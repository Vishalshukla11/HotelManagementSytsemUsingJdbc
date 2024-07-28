package InsertDataExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertdataExample1
{
public static void main(String args[])
{
    String url="jdbc:mysql://127.0.0.1:3306/mydatabase1";
    String username="root";
    String password="Pankaj";
    String query="insert into employee(id,name,job_title,salary) value(4,'Rampal baniya ','fullstackDeveloper',87000.0);";

    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded successfully!!!");
        
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    try {

        Connection c= DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully!!");
        Statement stat = c.createStatement();
        int rowsAffected = stat.executeUpdate(query);
        
        if(rowsAffected>0)
        {
            System.out.println("Insert successfull. " +rowsAffected + " rows affected");
        }
        stat.close();
        c.close();
        System.out.println();
        System.out.println("Connection closed successfully!!!");
        
    } catch (Exception e) {
        System.out.println(e.getMessage());
        
    }
}
}