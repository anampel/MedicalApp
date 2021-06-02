package beans;

import DAOs.AppointmentDAO;
import org.postgresql.util.PGmoney;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

/**
*Create an AppointmentBean, including characteristics from classes PatientBean and DoctorBean
* */

public class AppointmentBean extends PatientBean {
    Date date ;
    String kind_of_examination;
    String participation;
    Scanner console = new Scanner(System.in);
    /**
     *Constructor
     * */

    public AppointmentBean() {
    }

    /**
     *Just as Java doesn't support multiple inheritance, we created a private inner class that extends a second class DoctorBean.
     * */
    private static class Appointment1 extends DoctorBean {
        /**
         *Constructor
         * */
//        public Appointment1(String username, String name, String surname, String phone, String specialty) {
//            super(username, name, surname, phone, specialty);
//        }

        public Appointment1() {
        }
    }
    /**
     *Getters & Setters
     *
     *
     * @return*/
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getKind_of_examination() {
        return kind_of_examination;
    }

    public void setKind_of_examination(String kind_of_examination) {
        this.kind_of_examination = kind_of_examination;
    }

    public String getParticipation() {
         return participation;
    }

        public void setParticipation(String participation) {
        this.participation = participation;

    }
    /**
     *Create an appointment
     * */
    public void createAppointment() throws SQLException, ClassNotFoundException {
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        appointmentDAO.insertNewAppointment(this);
    }
}


