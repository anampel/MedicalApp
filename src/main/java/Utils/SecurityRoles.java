package Utils;

public abstract class SecurityRoles {
    /**
     *Roles
     * */
    public static final String roleAdmin = "ADMIN";
    public static final String roleDoctor = "DOCTOR";
    public static final String rolePatient = "PATIENT";

    /**
     * Jsp pages that correspond to roles
     * */

    public static final String AdminPage = "/jsp/admin.jsp";
    public static final String DoctorPage = "/jsp/doctor.jsp";
    public static final String PatientPage = "/jsp/patient.jsp";

}

