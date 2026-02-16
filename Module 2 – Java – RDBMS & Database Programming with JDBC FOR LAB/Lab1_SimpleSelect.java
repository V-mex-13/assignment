
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lab1_SimpleSelect {

    public static void main(String[] args) {
        // SQL query to select all students
        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Connected to database successfully.");
            System.out.println("ID | First Name | Last Name | Email");
            System.out.println("----------------------------------------------");

            // Iterate through the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");

                System.out.printf("%d | %s | %s | %s%n", id, fname, lname, email);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
