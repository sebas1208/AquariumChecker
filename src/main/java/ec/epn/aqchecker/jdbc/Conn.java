package ec.epn.aqchecker.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/AcuariumChecker";
            conn = DriverManager.getConnection(url, "sebastian", "sebastian");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nullo Class Not Found");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nullo Sql");
            return null;
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
