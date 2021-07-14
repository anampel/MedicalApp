package servlets;

import beans.AppointmentBean;
import beans.PatientBean;
import DAOs.PatientDAO;
import beans.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static Utils.SecurityRoles.*;

/**
 * Servlet that manage information about Patients
 * */
//@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
    /**
     * Constructor
     * */
    public PatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * doPost() method handle the info from the user,
     * takes access to the DB through the DAO class,
     * calls searchAppointmentHistory() from the AppointmentBean to provide the appointment's history,
     * validates the input and call the createDynPage() method to provide an error message
     * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute("user");

        PatientDAO patientDAO = new PatientDAO();
        try {
            PatientBean patient = patientDAO.findPatient(user);
            String destPage = "/jsp/login.jsp";

            if (patient != null) {
                session.setAttribute("patient", patient);
                destPage = "/jsp/patient.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

}
