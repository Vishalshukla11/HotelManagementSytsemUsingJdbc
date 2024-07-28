package DeleteData;

import java.sql.*;

public class DeleteDataExample {
    public static void main(String args[]) {
        String url = "jdbc:mysql://127.0.0.1:3306/mydatabase1";
        String userName = "root";
        String password = "Pankaj";
        String query = "Delete from employee where id in (2,4);";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully..");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection c = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection Established Successfully!!");
            Statement sta = c.createStatement();
            int rowsAffected = sta.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Delection successfull " + rowsAffected + "rows affected");
            } else {
                System.out.println("Delection failed.");
            }
            sta.close();
            c.close();
            System.out.println("Connection closed");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}