package jdbc_labs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Lab2_CRUD {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Insert  2. Update  3. Delete  4. View  5. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    insert();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    view();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void insert() {
        System.out.print("Fname: ");
        String fn = scanner.nextLine();
        System.out.print("Lname: ");
        String ln = scanner.nextLine();
        System.out.print("Email: ");
        String em = scanner.nextLine();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO students(fname,lname,email) VALUES(?,?,?)")) {
            ps.setString(1, fn);
            ps.setString(2, ln);
            ps.setString(3, em);
            ps.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        System.out.print("ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New Fname: ");
        String fn = scanner.nextLine();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("UPDATE students SET fname=? WHERE id=?")) {
            ps.setString(1, fn);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void delete() {
        System.out.print("ID to delete: ");
        int id = scanner.nextInt();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void view() {
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM students");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.printf("%d | %s %s | %s%n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
