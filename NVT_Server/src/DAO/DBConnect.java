package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Ngọ Văn Trọng
 */
public class DBConnect {
	
    private String jdbcURL = "jdbc:mysql://localhost:3306/game_sort_chat?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static DBConnect instance;
    private Connection connection;

    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    private DBConnect() {

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
