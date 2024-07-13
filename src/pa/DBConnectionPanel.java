package pa;

import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionPanel extends VBox {

    private Connection conn;

    public void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batchupdate_database", "root", "");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRecordsWithoutBatchUpdates() throws SQLException {
        Statement stmt = conn.createStatement();
        for (int i = 0; i < 1000; i++) {
            double num1 = Math.random();
            double num2 = Math.random();
            double num3 = Math.random();

            String query = "INSERT INTO Temp (num1, num2, num3) VALUES (" + num1 + ", " + num2 + ", " + num3 + ")";
            stmt.executeUpdate(query);
        }
        stmt.close();
    }

    public void insertRecordsWithBatchUpdates() throws SQLException {
        String query = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        for (int i = 0; i < 1000; i++) {
            double num1 = Math.random();
            double num2 = Math.random();
            double num3 = Math.random();

            pstmt.setDouble(1, num1);
            pstmt.setDouble(2, num2);
            pstmt.setDouble(3, num3);

            pstmt.addBatch();
        }

        pstmt.executeBatch();
        pstmt.close();
    }
}
