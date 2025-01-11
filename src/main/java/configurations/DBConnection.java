package configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url ="jdbc:postgresql://localhost:5432/postgres" ;
    private static final String username ="postgres" ;
    private static final String password ="12345" ;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Database unsuccessful ... :(");
            throw new RuntimeException(e);
        }
    }
}
