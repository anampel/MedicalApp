package DAOs;

import beans.AppointmentBean;
import beans.UserBean;

import java.sql.*;

/**
 * Data Access Object Class to manage information from DB about Users
 */
public class UserDAO extends DBUtils {
    /**
     * Find all the characteristics of the User (from the DB) with the provided username and phone
     * and set the values to the corresponding Bean class
     *
     * @param username The username of the provided user
     * @param password The phone of the provided user
     */
    public UserBean findUser(String username, String password) throws SQLException,
            ClassNotFoundException {
        Connection connection = init();
        String sql = "SELECT * FROM users WHERE username = ? and password = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        UserBean user = null;

        if (result.next()) {
            user = new UserBean();
            user.setUsername(username);
            user.setName(result.getString("name"));
            user.setSurname(result.getString("surname"));
            user.setRole(result.getString("role"));
            user.setPhone(result.getString("phone"));
            user.setPassword(password);
        }

        connection.close();

        return user;
    }
    public void insertUser(UserBean user)
            throws SQLException,
            ClassNotFoundException {
        Connection connection = init();
        String sql = "INSERT INTO users (username, name, surname, phone, role, password) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getName());
        statement.setString(3, user.getSurname());
        statement.setString(4, user.getPhone());
        statement.setString(5, user.getRole());
        statement.setString(6, user.getPassword());
        statement.executeUpdate();
    }

    public void deleteUser(String username) {

        try {
            Connection connection = init();
            String sql = ("delete from users where username=?");
            PreparedStatement statement = connection.prepareStatement(sql);
            // Parameters start with 1
            statement.setString(1, username);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}