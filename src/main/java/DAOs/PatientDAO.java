package DAOs;

import beans.DoctorBean;
import beans.PatientBean;
import beans.UserBean;

import java.sql.*;

/**
 * Data Access Object Class to manage information from DB about Patients
 * */
public class PatientDAO extends DBUtils{
    /**
     * Find all the characteristics of the Patient (from the DB) with the provided username and password
     * and set the values to the corresponding Bean class
     * @param user The user's object
     * */
    public PatientBean findPatient(UserBean user) throws SQLException,
            ClassNotFoundException {
        String username = user.getUsername();
        Connection connection = init();
        String sql = "SELECT * FROM patient WHERE username = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();

        PatientBean patient = null;

        if (result.next()) {
            patient = new PatientBean();
            patient.setUsername(username);
            patient.setName(user.getName());
            patient.setSurname(user.getSurname());
            patient.setRole(user.getRole());
            patient.setPhone(user.getPhone());
            patient.setPassword(user.getPassword());
            patient.setAmka(result.getString("amka"));
            patient.setAsfaleia(result.getString("asfaleia"));
        }

        connection.close();

        return patient;
    }

    public void insertPatient(PatientBean patient)
            throws SQLException,
            ClassNotFoundException {
        Connection connection = init();
        String sql = "INSERT INTO patient (username, amka, asfaleia) VALUES (?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, patient.getUsername());
        statement.setString(2, patient.getAmka());
        statement.setString(3, patient.getAsfaleia());
        statement.executeUpdate();
    }

    public void deletePatient(String username) {

        try {
            Connection connection = init();
            String sql = ("delete from patient where username=?");
            PreparedStatement statement = connection.prepareStatement(sql);
            // Parameters start with 1
            statement.setString(1, username);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
