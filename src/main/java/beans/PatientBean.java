package beans;


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


}
