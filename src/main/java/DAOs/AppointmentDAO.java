package DAOs;

import beans.AppointmentBean;
import java.sql.*;

/**
 * Data Access Object Class to manage information from DB about Appointments
 * */
public class AppointmentDAO extends DBUtils{
        /**
         * Find all the appointments for a provided user (from the DB) and set the values to the corresponding Bean class
         * @param username The username of the provided user
         * */
        public AppointmentBean findAppointment(String username) throws SQLException, ClassNotFoundException {
        Connection connection = init();
        String sql = "SELECT * FROM appointment WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);

        ResultSet result = statement.executeQuery();

        AppointmentBean appointment = null;
        if (result.next()) {
            appointment = new AppointmentBean();
            appointment.setUsername(username);
            appointment.setName(result.getString("name"));
            appointment.setSurname(result.getString("surname"));
            appointment.setPhone(result.getString("phone"));
            appointment.setAmka(result.getString("amka"));
            appointment.setAsfaleia(result.getString("asfaleia"));
            appointment.setDate(result.getDate("date"));
            appointment.setKind_of_examination(result.getString("examination"));
            appointment.setParticipation(result.getString("participation"));
        }
        connection.close();
        return appointment;
    }

    /**
     * Insert new appointments to the DB with the provided characteristics from the AppointmentBean class
     * @param appointment The set of characteristics that included in the AppointmentBean class
     * */
    public void insertNewAppointment(AppointmentBean appointment)
                                    throws SQLException,
                                    ClassNotFoundException {
        Connection connection = init();
        String sql = "INSERT INTO appointment (username, name, surname, phone, amka, asfaleia, date, examination, participation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, appointment.getUsername());
        statement.setString(1, appointment.getName());
        statement.setString(1, appointment.getSurname());
        statement.setString(1, appointment.getPhone());
        statement.setString(1, appointment.getAmka());
        statement.setString(1, appointment.getAmka());
        statement.setDate(1, (Date) appointment.getDate());
        statement.setString(1, appointment.getKind_of_examination());
        statement.setString(1, appointment.getParticipation());
        statement.executeUpdate();

    }
}
