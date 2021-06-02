package DAOs;

import beans.PatientBean;

import java.sql.*;

/**
 * Data Access Object Class to manage information from DB about Patients
 * */
public class PatientDAO extends DBUtils{
    /**
     * Find all the characteristics of the Patient (from the DB) with the provided username and phone
     * and set the values to the corresponding Bean class
     * @param username The username of the provided user
     * @param phone The phone of the provided user
     * */
    public PatientBean findPatient(String username, String phone) throws SQLException,
            ClassNotFoundException {
        Connection connection = init();
        String sql = "SELECT * FROM patient WHERE username = ? and phone = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, phone);

        ResultSet result = statement.executeQuery();

        PatientBean patient = null;

        if (result.next()) {
            patient = new PatientBean();
            patient.setUsername(username);
            patient.setName(result.getString("name"));
            patient.setSurname(result.getString("surname"));
            patient.setAmka(result.getString("amka"));
            patient.setAsfaleia(result.getString("asfaleia"));
            patient.setPhone(phone);
        }

        connection.close();

        return patient;
    }
}
