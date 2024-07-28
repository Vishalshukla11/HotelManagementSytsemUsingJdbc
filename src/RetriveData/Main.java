package RetriveData;

import java.sql.*;

public class Main {
    public static void main(String args[]) {

        String url = "jdbc:mysql://127.0.0.1:3306/mydatabase1";
        String username = "root";
        String password = "Pankaj";

        String query = "select * from employee;";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }

        try {
            // here we are connecting with database
            Connection c = DriverManager.getConnection(url, username, password);
            System.out.println("Connection stablish successfully.");

            // now using the stored c stance data and creating the data with the help of
            // createStatment
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                int idd = result.getInt("id");
                String namee = result.getString("name");
                String jobtitle = result.getString("job_title");
                double salary = result.getDouble("salary");

                System.out.println();
                System.out.println("======================");
                System.out.println("ID: "+idd);
                System.out.println("Name: " +namee);
                System.out.println("job_Title: " +jobtitle);
                System.out.println("salary: " + salary);
                
            }
            result.close();
            c.close();
            st.close();
            System.out.println("Connection close successfully.!!") ;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}