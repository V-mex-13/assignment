package jdbc_labs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Lab4_StoredProcedure {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection();
                CallableStatement cstmt = conn.prepareCall("{CALL get_student_name(?, ?)}")) {

            cstmt.setInt(1, id);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.execute();

            String name = cstmt.getString(2);
            System.out.println("Result from Procedure: " + (name != null ? name : "Not found"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
