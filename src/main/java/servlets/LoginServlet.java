package servlets;

import Utils.Security;
import Utils.SecurityRoles;
import beans.UserBean;
import DAOs.UserDAO;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import static Utils.SecurityRoles.*;
import static Utils.SecurityRoles.rolePatient;

/**
 * Servlet that manage the Login of a user
 */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public LoginServlet() {
        super();
    }

    /**
     * doPost() method handle the info from the user,
     * takes access to the DB through the DAO class
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = null;
        password = Security.hashPassword(request.getParameter("password"));
        System.out.println("PWD:"+password);


        UserDAO userDao = new UserDAO();

        try {
            UserBean user = userDao.findUser(username, password);
            /**
             *Input Validation
             * */
            if (username == null || password == null) {
                createDynPage(response, "Fill in every field");
            }

            String destPage = "/jsp/login.jsp";

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destPage = "/jsp/home.jsp";
            } else {
                String message = "Invalid username/password";
                createDynPage(response, message);
            }


            if (SecurityRoles.roleAdmin.equals(user.getRole())) {
                destPage = "/admin";
                request.setAttribute("role", roleAdmin);
            } else if (SecurityRoles.roleDoctor.equals(user.getRole())) {
                destPage = "/doctor";
                request.setAttribute("role", roleDoctor);
            } else if (SecurityRoles.rolePatient.equals(user.getRole())) {
                destPage = "/patient";
                request.setAttribute("role", rolePatient);

            } else {
                createDynPage(response, "Permission denied");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (action.equalsIgnoreCase("admin")) {
            forward = AdminPage;
            request.setAttribute("role", roleAdmin);
        } else if (action.equalsIgnoreCase("doctor")) {
            forward = DoctorPage;
            request.setAttribute("role", roleDoctor);
        } else if (action.equalsIgnoreCase("patient")) {
            forward = PatientPage;
            request.setAttribute("role", rolePatient);
        } else {
            System.out.println("Invalid action");
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Create a dynamic error page with a provided error message
     *
     * @param message The message that provided from the doPost() method
     */
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