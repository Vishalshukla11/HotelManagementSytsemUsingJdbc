package HotelReservationSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static HotelReservationService reservationservice = new HotelReservationService();

    public static void main(String args[]) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Failed to eltablished database connection. Exiting application.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Hotel Management System");
            System.out.println("1. New Room Reservation.");
            System.out.println("2. Check Reservation.");
            System.out.println("3. Get Room no.");
            System.out.println("4. Update Reservation.");
            System.out.println("5. Delete Reservation.");
            System.out.println("6. Exit!!!");
            System.out.println("Please Enter your choice...");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    reservationservice.reserveRoom(connection, sc);
                    break;
                case 2:
                    // reservationservice.viewReservations(connection);
                    reservationservice.viewReservations(connection);

                    break;
                case 3:
                    reservationservice.getRoomNumber(connection, sc);
                    break;
                case 4:
                    reservationservice.updateReservation(connection, sc);
                    break;
                case 5:
                    reservationservice.DeleteREservation(connection, sc);
                    break;
                case 6:
                    reservationservice.exit();
                    break;

                default:
                    System.out.println("Invalid choice. try again.");
            }

        }

    }
}