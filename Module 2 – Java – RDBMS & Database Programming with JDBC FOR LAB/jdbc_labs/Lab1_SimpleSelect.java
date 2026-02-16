package jdbc_labs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lab1_SimpleSelect {

    public static void main(String[] args) {
        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            System.out.println("ID | First Name | Last Name | Email");
            System.out.println("----------------------------------------------");

            while (rs.next()) {
                System.out.printf("%d | %s | %s | %s%n",
                        rs.getInt("id"), rs.getString("fname"),
                        rs.getString("lname"), rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
