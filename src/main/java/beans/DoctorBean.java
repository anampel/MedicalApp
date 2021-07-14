package beans;

/**
 *Child of UserBean, concerns Doctors
 * */
public class DoctorBean extends UserBean {
    public String specialty;

    /**
     *Constructor
     * */
    public DoctorBean() {
    }

    /**
     *Getters & Setters
     * */
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
