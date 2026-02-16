
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Lab4_StoredProcedure {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID to retrieve name: ");
        int studentId = scanner.nextInt();

        // SQL to call the stored procedure: get_student_name(IN id, OUT fullname)
        String sql = "{CALL get_student_name(?, ?)}";

        try (Connection conn = DBConnection.getConnection();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            // Set IN parameter
            cstmt.setInt(1, studentId);

            // Register OUT parameter
            cstmt.registerOutParameter(2, Types.VARCHAR);

            // Execute stored procedure
            cstmt.execute();

            // Retrieve OUT parameter
            String fullName = cstmt.getString(2);

            if (fullName != null) {
                System.out.println("Student Name: " + fullName);
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
