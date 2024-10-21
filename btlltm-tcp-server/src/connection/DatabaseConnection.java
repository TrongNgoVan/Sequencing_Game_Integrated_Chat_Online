package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
    private String jdbcURL = "jdbc:mysql://localhost:3306/btlltm?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }
    
    public Connection getConnection() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Connected to Database.");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
	}
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
