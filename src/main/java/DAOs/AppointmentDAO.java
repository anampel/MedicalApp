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

    public static String appointment_doctor_sql = "SELECT * FROM appointment WHERE user_doctor = ?";
    public static String appointment_patient_sql = "SELECT * FROM appointment WHERE user_patient = ?";

        /**
         * Find all the appointments for a provided user (from the DB) and set the values to the corresponding Bean class
         * @param user The user's object of the provided user
         * */
        public List<AppointmentBean> findAppointment(UserBean user) throws SQLException, ClassNotFoundException {
        Connection connection = init();
        List<AppointmentBean> list = new ArrayList<AppointmentBean>();
        AppointmentBean appointment = null;

        String username = user.getUsername();

        PreparedStatement statement = connection.prepareStatement(SecurityRoles.roleDoctor.equals(user.getRole())? appointment_doctor_sql : appointment_patient_sql);
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
        String sql = "INSERT INTO appointment (user_patient, amka, asfaleia, date, examination, participation, user_doctor) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, appointment.getUsername());
        statement.setString(2, appointment.getAmka());
        statement.setString(3, appointment.getAsfaleia());
        statement.setDate(4, (Date) appointment.getDate());
        statement.setString(5, appointment.getKind_of_examination());
        statement.setString(6, appointment.getParticipation());
        statement.setString(7, appointment.getUser_doctor());
        statement.executeUpdate();
        }

    /**
     * Delete an appointment from the DB for the given date
     * @param date The date of the appointment
     * */

    public static String deleteDoctor = "DELETE FROM appointment where user_doctor = ? and  date=?";
    public static String deletePatient = "DELETE FROM appointment where user_patient = ? and  date=?";
    public void deleteAppointment(Date date, String username, String role){
        try {
            Connection connection = init();


            PreparedStatement statement = connection.prepareStatement(SecurityRoles.roleDoctor.equals(role) ? deleteDoctor : deletePatient);
            // Parameters start with 1
            statement.setString(1, username);
            statement.setDate(2, date);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
