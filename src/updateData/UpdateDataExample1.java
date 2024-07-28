package updateData;

import java.sql.*;

class UpdateDataExample1 {
    public static void main(String args[]) {
        String url = "jdbc:mysql://127.0.0.1:3306/mydatabase1";
        String userName = "root";
        String password = "Pankaj";
        String query = "update employee set name='vipul', job_title='Tester' where id=4; ";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection Establish successfully!!");
            Statement state = con.createStatement();
            int RowsAffected = state.executeUpdate(query);

            if(RowsAffected>0)
            {
                System.out.println("Updation successfull " + RowsAffected + " Rows affected ");
            }
            else
            {
                System.out.println("updation failed.");
            }
            con.close();
            state.close();
            System.out.println("Connection closed successfully!!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}