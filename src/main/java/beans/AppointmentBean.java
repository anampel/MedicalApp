package beans;

import java.util.Date;
import java.util.Scanner;

/**
*Create an AppointmentBean, including characteristics from classes PatientBean and DoctorBean
* */

public class AppointmentBean extends PatientBean {
    String user_doctor;
    Date date ;
    String kind_of_examination;
    String participation;
    Scanner console = new Scanner(System.in);
    /**
     *Constructor
     * */

    public AppointmentBean() {
    }

    public String getUser_doctor() {
        initalPartic_Doctors();
        return user_doctor;
    }

    public void setUser_doctor(String user_doctor) {
        this.user_doctor = user_doctor;
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
        initalPartic_Doctors();
         return participation;
     }

        public void setParticipation(String participation) {
        this.participation = participation;

    }
    /**
     * Initialize participation
     * */
    public void initalPartic_Doctors(){
        if (getKind_of_examination().equals("Ears check")) {
            setParticipation("10,00 €");
            setUser_doctor("doctor");
        }
        else if (getKind_of_examination().equals("Endocrinological")) {
            setParticipation("23,50 €");
            setUser_doctor("fiyta");
        }
        else if (getKind_of_examination().equals("Cardiological")){
            setParticipation("15,40 €");
            setUser_doctor("kapa");
        }
        else if (getKind_of_examination().equals("Blood test")){
            setParticipation("8,50 €") ;
            setUser_doctor("mofihily1");
        }else {
            setParticipation("") ;
            setUser_doctor("");
        }

    }

}


