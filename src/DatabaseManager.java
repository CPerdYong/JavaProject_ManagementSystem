import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    private Connection connection;
    // Loading JDBC Driver
    public DatabaseManager() {
        try {
            // Loading JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Provide the URL to the database with your own username and password
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java02database", "root", "cheperd@2002");
        }
        // Print exceptions that occur during the connection process
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
