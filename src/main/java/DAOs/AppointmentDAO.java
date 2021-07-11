package DAOs;

import Utils.SecurityRoles;
import beans.AppointmentBean;
import beans.UserBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Data Access Object Class to manage information from DB about Appointments
 * */
public class AppointmentDAO extends DBUtils{
        /**
         * Find all the appointments for a provided user (from the DB) and set the values to the corresponding Bean class
         * @param user The user's object of the provided user
         * */
        public List<AppointmentBean> findAppointment(UserBean user) throws SQLException, ClassNotFoundException {
        Connection connection = init();
        List<AppointmentBean> list = new ArrayList<AppointmentBean>();
        AppointmentBean appointment = null;

        String username = user.getUsername();
        String sql = "SELECT * FROM appointment WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        while (result.next()) {

            /**
             * Fields username and date are both accessed by doctors and patients
             * */
            appointment = new AppointmentBean();
            appointment.setUsername(username);
            appointment.setDate(result.getDate("date"));

            /**
             * Fields amka, asfaleia, examination and participation are able to be accessed only by patients
             * */
            if (SecurityRoles.rolePatient.equals(user.getRole())) {
                appointment.setAmka(result.getString("amka"));
                appointment.setAsfaleia(result.getString("asfaleia"));
                appointment.setKind_of_examination(result.getString("examination"));
                appointment.setParticipation(result.getString("participation"));
            }
            list.add(appointment);
        }
        connection.close();
        return list;
    }

    /**
     * Insert new appointments to the DB with the provided characteristics from the AppointmentBean class
     * @param appointment The set of characteristics that included in the AppointmentBean class
     * */
    public void insertNewAppointment(AppointmentBean appointment)
                                    throws SQLException,
                                    ClassNotFoundException {
        Connection connection = init();
        String sql = "INSERT INTO appointment (username, amka, asfaleia, date, examination, participation) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, appointment.getUsername());
        statement.setDate(1, (Date) appointment.getDate());
        statement.setString(1, appointment.getAmka());
        statement.setString(1, appointment.getAsfaleia());
        statement.setString(1, appointment.getKind_of_examination());
        statement.setString(1, appointment.getParticipation());
        statement.executeUpdate();
        }

    /**
     * Delete an appointment from the DB for the given date
     * @param date The date of the appointment
     * */
    public void deleteAppointment(Date date){
        try {
            Connection connection = init();
            String sql = ("DELETE FROM appointment where date=?");
            PreparedStatement statement = connection.prepareStatement(sql);
            // Parameters start with 1
            statement.setDate(1, date);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
