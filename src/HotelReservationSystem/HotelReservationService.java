package HotelReservationSystem;

//import HotelReservationSystem.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;

public class HotelReservationService {
    // DatabaseConnection dc= new DatabaseConnection();
    public void reserveRoom(Connection connection, Scanner sc) {
        try {
            System.out.print("Enter guest name: ");
            String guestName = sc.next();
            sc.nextLine();
            System.out.print("Enter room number: ");
            int roomNumber = sc.nextInt();
            System.out.print("Enter contact number: ");
            String contactNumber = sc.next();

            String sqlquery = "INSERT INTO reservations (guest_name, room_no, contact_number) " +
                    "VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "')";

            try (Statement statement = connection.createStatement()) {
                int affectedRows = statement.executeUpdate(sqlquery);
                if (affectedRows > 0) {
                    System.out.println("Reservation successfull!");
                } else {
                    System.out.println("Reservation failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void viewReservations(Connection connection) {
        String sqlQuery = "Select reservation_id,guest_name,room_no,contact_number,reservation_date from reservations;";
        try (Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sqlQuery)) {
           

            System.out.println("current Reservations: ");
            System.out.println(
                    "+----------------+----------+----------------+--------------------+------------------------+");
            System.out.println(
                    "| Reservation ID | Guest    | Room Number    | Contact Number     |  Reservation Date      |");
            System.out.println(
                    "+----------------+----------+----------------+--------------------+------------------------+");

            while (result.next()) {
                int reservationId = result.getInt("Reservation_id");
                String guestName = result.getString("Guest_Name");
                int roomNumber = result.getInt("room_no");
                String contactnumber = result.getString("contact_number");
                String reservationdate = result.getTimestamp("reservation_date").toString();

                // format and display the reservation date in a table -like format
                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                reservationId, guestName, roomNumber, contactnumber, reservationdate);

            }
            System.out.println(
                    "+----------------+----------+----------------+--------------------+------------------------+");

        } 
        catch (Exception e) {
            e.getMessage();
        }

    }
   

    public void getRoomNumber(Connection connection, Scanner sc) {
        try {
            System.out.println("Enter reservation ID:");
            int reservation_id = sc.nextInt();
            System.out.println("Enter guest Name");
            String guestName = sc.next();

            String sqlQuery = "Select room_no from reservations Where reservation_id = " + reservation_id +
                    " And guest_name='" + guestName + "';";
            try (Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(sqlQuery)) {
                if (result.next()) {
                    int roomNumber = result.getInt("room_no");
                    System.out.println("Room number of reservation ID " + reservation_id + " and guest " + guestName
                            + " is:- " + roomNumber);
                } else {
                    System.out.println("Reservation not found for the given id and guest name.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReservation(Connection connection, Scanner sc) {
        try {
            System.out.println("Enter reservation Id to update: ");
            int reservation_id = sc.nextInt();
            sc.nextLine();

            if (!reservationExists(connection, reservation_id)) {
                System.out.println("Reservation not found for given id");
                return;
            }
            System.out.println("Enter new guest name:");
            String newGuestName = sc.nextLine();
            System.out.println("Enter new Room number:");
            int newRoomNumber = sc.nextInt();
            System.out.println("Enter new contact number:");
            String newContactNumber = sc.next();

            String sqlQuery = "Update reservations set guest_Name= '" + newGuestName + "'," +
                    "room_no= " + newRoomNumber + "," +
                    "contact_number = '" + newContactNumber + "'" +
                    "where reservation_id=" + reservation_id ;

            try (Statement statement = connection.createStatement()) {
                int affactedRows = statement.executeUpdate(sqlQuery);
                if (affactedRows > 0) {
                    System.out.println("Reservation updated successfully!.");
                } else {
                    System.out.println("Reservation update Failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteREservation(Connection connection, Scanner sc) {
        try {
            System.out.println("Enter reservation Id to delete:");
            int reservation_id = sc.nextInt();
            if (!reservationExists(connection, reservation_id)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }
            String sqlQuert = "Delete from reservations where reservation_id= " + reservation_id;
            try (Statement statement = connection.createStatement()) {
                int affectedRpws = statement.executeUpdate(sqlQuert);
                if (affectedRpws > 0) {
                    System.out.println("Reservation deleted successfully!.");
                } else {
                    System.out.println("Reservation deletion failed");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean reservationExists(Connection connection, int reservation_id) {
        try {
            String sqlQuery = "Select reservation_id from reservations where reservation_id = " + reservation_id;

            try (Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(sqlQuery)) {
                return result.next(); // if there us a result the reservation exists

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // handle database errors as needed
        }

    }

    public void exit() {
        System.out.print("Exiting System");
        int i = 5;
        while (i != 0) {
            System.out.print(".");

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            i--;

        }
        System.out.println();
        System.out.println("ThankYou for using hotel Reservation System!!!");
    }

}