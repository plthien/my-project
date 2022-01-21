package Repository.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    public static Connection connection;
    private static String jdbcURL = "jdbc:mysql://localhost:3306/Furama_Resort_Management?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "Thanhhiencodegym@1";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
