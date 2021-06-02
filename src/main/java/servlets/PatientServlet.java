package servlets;

import beans.AppointmentBean;
import beans.PatientBean;
import DAOs.PatientDAO;

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

import static beans.UserBean.regexPhone;
/**
 * Servlet that manage information about Patients
 * */
@WebServlet(name = "/PatientServlet")
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
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        /**
         *Input Validation
         * */
        if(username == null || phone == null){
            createDynPage(response, "Fill in every field");
        }
        if (!phone.matches(regexPhone)) {
            createDynPage(response, "Invalid username/phone number");
        }
        PatientDAO patientDAO = new PatientDAO();
        try {
            PatientBean patient = patientDAO.findPatient(username, phone);
            AppointmentBean appointment = patient.searchAppointmentHistory(username);
            String destPage = "/jsp/login.jsp";

            if (patient != null) {
                HttpSession session = request.getSession();
                session.setAttribute("patient", patient);
                session.setAttribute("appointment", appointment);
                destPage = "/jsp/home.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Create a dynamic error page with a provided error message
     * @param message The message that provided from the doPost() method
     * */
    private void createDynPage(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Login Error</title></head>");
        out.println("<body>");
        out.println("<p>" + message + "</p>");
        out.println("<a href=\"/MedicalApp/index.jsp\">Go to main page</a>");
        out.println("</body></html>");
    }
}
