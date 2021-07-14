package servlets;
import DAOs.*;
import beans.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import static Utils.SecurityRoles.*;
/**
 * Servlet that manage information about admins
 * */
//@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    /**
     * Constructor
     * */
    public AdminServlet() {
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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        UserBean inputUser = new UserBean();

        //Initialization
        UserBean user = (UserBean)session.getAttribute("user");
        UserDAO userDAO = new UserDAO();
        PatientDAO patientDAO = new PatientDAO();
        PatientBean patientBean = new PatientBean();
        DoctorDAO doctorDAO = new DoctorDAO();
        DoctorBean doctorBean = new DoctorBean();

        AdminDAO AdminDAO = new AdminDAO();
        try {
            AdminBean admin = AdminDAO.findAdmin(user);
            String destPage = "/jsp/login.jsp";

            if (admin != null) {
                session.setAttribute("admin", admin);
                destPage = "/jsp/admin.jsp";
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);


            if (action.equalsIgnoreCase("insertDoctor")) {

                //request general User's characteristics and set them in an inputUser object
                String inputUsername = request.getParameter("usernameDoc");
                String inputpassword = request.getParameter("passwordDoc");

                //validate that the input user does not exist
                UserBean userinput = userDAO.findUser(inputUsername, inputpassword);
                if (userinput != null){
                    String message = "The user already exists";
                    createDynPage(response, message);
                }

                //request general User's characteristics and set them in an inputUser object
                inputUser.setUsername(inputUsername);
                inputUser.setName(request.getParameter("nameDoc"));
                inputUser.setSurname(request.getParameter("surnameDoc"));
                inputUser.setPhone(request.getParameter("phoneDoc"));
                inputUser.setRole(roleDoctor);
                inputUser.setPassword(inputpassword);
                userDAO.insertUser(inputUser);

                //request Doctor's characteristics and set them in an object
                doctorBean.setUsername(inputUsername);
                doctorBean.setSpecialty(request.getParameter("specialtyDoc"));
                doctorDAO.insertDoctor(doctorBean);

            }
            if (action.equalsIgnoreCase("insertPatient")) {
                //request general User's characteristics and set them in an inputUser object
                String inputUsername = request.getParameter("usernamePat");
                String inputpassword = request.getParameter("passwordPat");

                //validate that the input user does not exist
                UserBean userinput = userDAO.findUser(inputUsername, inputpassword);
                if (userinput != null){
                    String message = "The user already exists";
                    request.setAttribute("message", message);
                    createDynPage(response, message);
                }

                //request general User's characteristics and set them in an inputUser object
                inputUser.setUsername(inputUsername);
                inputUser.setName(request.getParameter("namePat"));
                inputUser.setSurname(request.getParameter("surnamePat"));
                inputUser.setPhone(request.getParameter("phonePat"));
                inputUser.setRole(rolePatient);
                inputUser.setPassword(inputpassword);
                userDAO.insertUser(inputUser);

                //request Patient's characteristics and set them in an object
                patientBean.setUsername(inputUsername);
                patientBean.setAmka(request.getParameter("amkaPat"));
                patientBean.setAsfaleia(request.getParameter("asfaleiaPat"));
                patientDAO.insertPatient(patientBean);

            }

            if (action.equalsIgnoreCase("deleteDoctor")) {
                String inputUsername = request.getParameter("delUsernameDoc");
                doctorDAO.deleteDoctor(inputUsername);
                userDAO.deleteUser(inputUsername);
                System.out.println("A doctor deleted");
            }
            if (action.equalsIgnoreCase("deletePatient")) {
                String inputUsername = request.getParameter("delUsernamePat");
                patientDAO.deletePatient(inputUsername);
                userDAO.deleteUser(inputUsername);
            }


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
        out.println("<head><title>Error</title></head>");
        out.println("<body>");
        out.println("<p>" + message + "</p>");
        out.println("</body></html>");
    }

}
