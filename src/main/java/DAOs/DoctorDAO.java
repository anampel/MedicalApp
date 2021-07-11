package DAOs;
import beans.DoctorBean;
import beans.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
/**
 * Data Access Object Class to manage information from DB about doctors
 * */
public class DoctorDAO extends DBUtils{
    /**
     * Find all the characteristics of the doctor (from the DB) with the provided username and password
     * and set the values to the corresponding Bean class
     * @param user The user's object
     * */
    public DoctorBean findDoctor(UserBean user) throws SQLException,
            ClassNotFoundException {
        String username = user.getUsername();
        Connection connection = init();
        String sql = "SELECT * FROM doctor WHERE username = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();

        DoctorBean doctor = null;

        if (result.next()) {
            doctor = new DoctorBean();
            doctor.setUsername(username);
            doctor.setName(user.getName());
            doctor.setSurname(user.getSurname());
            doctor.setRole(user.getRole());
            doctor.setPhone(user.getPhone());
            doctor.setPassword(user.getPassword());
            doctor.setSpecialty(result.getString("speciality"));
        }
        connection.close();
        return doctor;
    }

    public void insertDoctor(DoctorBean doctor)
            throws SQLException,
            ClassNotFoundException {
        Connection connection = init();
        String sql = "INSERT INTO doctor (username, speciality) VALUES (?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, doctor.getUsername());
        statement.setString(2, doctor.getSpecialty());
        statement.executeUpdate();
    }
    public void deleteDoctor(String username) {

        try {
            Connection connection = init();
            String sql = ("delete from doctor where username=?");
            PreparedStatement statement = connection.prepareStatement(sql);
            // Parameters start with 1
            statement.setString(1, username);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
