package DAOs;
import java.sql.*;

/**
 * Abstract Class for providing DB properties to other DAO classes
 * */
public abstract class DBUtils {
    public Connection init() throws ClassNotFoundException, SQLException {
        String jdbcURL = "jdbc:postgresql://localhost:5432/medicalappointments";
        String dbUser = "postgres";
        String dbPassword = "anampel21";
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        return connection;
    }
}
