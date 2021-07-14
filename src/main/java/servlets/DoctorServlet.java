package servlets;

import beans.DoctorBean;
import DAOs.DoctorDAO;
import beans.UserBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet that manage information about doctors
 * */
//@WebServlet("/doctor")
public class DoctorServlet extends HttpServlet {
    /**
     * Constructor
     * */
    public DoctorServlet() {
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

        DoctorDAO DoctorDAO = new DoctorDAO();
        try {
            DoctorBean doctor = DoctorDAO.findDoctor(user);
            String destPage = "/jsp/login.jsp";

            if (doctor != null) {
                session.setAttribute("doctor", doctor);
                destPage = "/jsp/doctor.jsp";
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

}
