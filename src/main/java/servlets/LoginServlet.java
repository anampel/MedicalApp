package servlets;

import beans.UserBean;
import DAOs.UserDAO;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Servlet that manage the Login of a user
 * */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * Constructor
     * */
    public LoginServlet() {
        super();
    }
    /**
     * doPost() method handle the info from the user,
     * takes access to the DB through the DAO class
     * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");

        UserDAO userDao = new UserDAO();

        try {
            UserBean user = userDao.findUser(username, phone);
            String destPage = "/jsp/login.jsp";

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destPage = "/jsp/home.jsp";
            } else {
                String message = "Invalid username/phone";
                request.setAttribute("message", message);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
}