package DAOs;
import beans.AdminBean;
import beans.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
/**
 * Data Access Object Class to manage information from DB about admins
 * */
public class AdminDAO extends DBUtils{
    /**
     * Find all the characteristics of the admin table with the provided user object
     * and set the values to the corresponding Bean class
     * @param user The user's object
     * */
    public AdminBean findAdmin(UserBean user) throws SQLException,
            ClassNotFoundException {
        String username = user.getUsername();
        Connection connection = init();
        String sql = "SELECT * FROM admin WHERE username = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();

        AdminBean admin = null;

        if (result.next()) {
            admin = new AdminBean();
            admin.setUsername(username);
            admin.setName(user.getName());
            admin.setSurname(user.getSurname());
            admin.setRole(user.getRole());
            admin.setPhone(user.getPhone());
            admin.setPassword(user.getPassword());
            admin.setId(result.getString("id"));
        }
        connection.close();
        return admin;
    }

}
