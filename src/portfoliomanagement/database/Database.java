package portfoliomanagement.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database connection class
 */
public class Database{

    // Method to establish and return a MySQL database connection
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/portfolio_pg"; // Database details
        String username = "root"; // MySQL credentials
        String password = ""; // Your MySQL password
        
        Connection con = null;
        
        try {
            // Establish the connection
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established successfully!");
        } catch (SQLException ex) {
             System.out.println("Exception in"+ ex);
             return null;
        }

        return con; // Return the connection object (could be null if failed)
    }
}
