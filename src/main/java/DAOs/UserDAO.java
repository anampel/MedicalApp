package DAOs;
import beans.UserBean;

import java.sql.*;

/**
 * Data Access Object Class to manage information from DB about Users
 * */
public class UserDAO extends DBUtils{
    /**
     * Find all the characteristics of the User (from the DB) with the provided username and phone
     * and set the values to the corresponding Bean class
     * @param username The username of the provided user
     * @param phone The phone of the provided user
     * */
    public UserBean findUser(String username, String phone) throws SQLException,
            ClassNotFoundException {
        Connection connection = init();
        String sql = "SELECT * FROM users WHERE username = ? and phone = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, phone);

        ResultSet result = statement.executeQuery();

        UserBean user = null;

        if (result.next()) {
            user = new UserBean();
            user.setUsername(username);
            user.setName(result.getString("name"));
            user.setSurname(result.getString("surname"));
            user.setPhone(phone);
        }

        connection.close();

        return user;
    }
}