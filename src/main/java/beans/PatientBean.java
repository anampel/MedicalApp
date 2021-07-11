package beans;

import DAOs.AppointmentDAO;

import java.sql.SQLException;

/**
 *Child of UserBean, concerns patients
 * */
public class PatientBean extends UserBean {
    public String amka;
    public String asfaleia; // (idiotikh/dhmosia)
    /**
     *Constructor
     * */
    public PatientBean() {
    }

    /**
     *Getters & Setters
     * */
    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getAsfaleia() {
        return asfaleia;
    }

    public void setAsfaleia(String asfaleia) {
        this.asfaleia = asfaleia;
    }

    /**
     *Register a user as patient
     * @param username  The username of the patient
     * @param name The name of the patient
     * @param surname The surname of the patient
     * @param phone The phone of the patient
     * @param amka The amka of the patient
     * @param asfaleia The asfaleia of the patient
     * */
    public void registration(String username, String name, String surname, String phone, String amka, String asfaleia){
        System.out.println("Registration completed successfully for the patient " + username);
    }
    /**
     *Searches appointment per doctor name
     * @param name  The name of the doctor
     * @param surnname The surname of the doctor
     * */
    public void searchAppointmentPerDoctor(String name, String surnname){

    }
    /**
     *Searches appointment per doctor's specialty
     * @param specialty  The specialty of the doctor
     * */
    public void searchAppointmentPerSpecialty(String specialty){

    }

    /**
     *Searches patient's appointments
     * @param username The username of the patient
     * @param amka  The amka of the patient
     * */
    public void searchMyAppointment(String username,String amka){

    }

    /**
     *Searches patient's appointments history
     * @param username The username of the patient
     * */
    public AppointmentBean searchAppointmentHistory(String username) throws SQLException, ClassNotFoundException {
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        AppointmentBean appointmentBean = (AppointmentBean) appointmentDAO.findAppointment(this);
        return appointmentBean;
    }
}
