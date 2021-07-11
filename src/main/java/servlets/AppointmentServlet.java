package servlets;

import DAOs.AppointmentDAO;
import Utils.SecurityRoles;
import beans.AppointmentBean;
import beans.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static Utils.SecurityRoles.DoctorPage;
import static Utils.SecurityRoles.PatientPage;

/**
 * Servlet that manage information about Appointments
 * */
//@WebServlet(name = "AppointmentServlet")
public class AppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        UserBean user = (UserBean) session.getAttribute("user");
        AppointmentDAO AppointmentDAO = new AppointmentDAO();

        try {
            AppointmentBean appointment = (AppointmentBean) AppointmentDAO.findAppointment(user);
            if (appointment != null) {
                session.setAttribute("appointment", appointment);
            }
            /**
             * Take input the input date from the user and format it
             * */
            String DateString = request.getParameter("date1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = (Date) sdf.parse(DateString);
//            appointment.setDate(date);
//
//            if (action.equalsIgnoreCase("cancelAppointment")) {
//                AppointmentDAO.deleteAppointment(date);
//            }

//            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//            dispatcher.forward(request, response);
            if (action.equalsIgnoreCase("enterAvailableDays")) {
                AppointmentDAO.deleteAppointment((java.sql.Date) date);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");

        UserBean user = (UserBean) session.getAttribute("user");
        AppointmentDAO AppointmentDAO = new AppointmentDAO();
        List<AppointmentBean> appointments = null;

        if (action.equalsIgnoreCase("findAppointment")) {
            try {
                appointments = AppointmentDAO.findAppointment(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            createDynPage(request, response ,appointments, user);

        }
}
    /**
     * Create a dynamic page which provide the Appointments to Doctors and Patients
     * @param appointments The AppointmentBean list object that provided from the doPost() method
     * @param user The UserBean object
     * */
    public void createDynPage(HttpServletRequest request, HttpServletResponse response, List<AppointmentBean> appointments, UserBean user) throws IOException {
//        String destPage = "jsp/"
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>" +
                "<title>APPOINTMENTS</title>" +
                "<style>\n" +
                "a:link, a:visited {\n" +
                "  background-color: #f44336;\n" +
                "  color: white;\n" +
                "  padding: 14px 25px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "}\n" +
                "\n" +
                "a:hover, a:active {\n" +
                "  background-color: red;\n" +
                "}\n" +
                "</style>" +
                "</head>");
        out.println("<body>");
        out.println("<table style=\"text-align: center; border: 1px solid black;\">");
        out.println("<tbody style=\"padding: 15px; text-align: left; font-family: \"Times New Roman\", Times, serif;\">");
        out.println("<tr><h1>Appointments</h1></tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("<tr><th><h2> Date   -    </h2></th>");
        out.println("<th><h2> Examination   -    </h2</th>");
        out.println("<th><h2> Participation </h2></th></tr>");
        out.println("</td>");
        out.println("<td>");
        //Loop the Appointments object and provide the date for both Doctors and Patients
        for (AppointmentBean appointment : appointments) {
            out.println("<tr style=\"text-align: center;\"><td>"+ appointment.getDate() + "</td>");
            //If the user is Patient provide also the Kind of examination and the participation
            if (SecurityRoles.rolePatient.equals(user.getRole())){
                out.println("<td>"+ appointment.getKind_of_examination() + "</td>");
                out.println("<td>"+ appointment.getParticipation() + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</td>");
        out.println("</tr>");
        out.println("</tbody>");
        out.println(" </table>");
        out.println(" </br>");
        out.println(" </br>");
        if (SecurityRoles.rolePatient.equals(user.getRole())){
//            destPage= PatientPage;
            out.println("<a href=\"/patient\">Go to main page</a>");
        }
        if (SecurityRoles.roleDoctor.equals(user.getRole())){
//            destPage = DoctorPage;
            out.println("<a href=\"/doctor\">Go to main page</a>");
        }
        out.println("</body></html>");
//        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//        dispatcher.forward(request, response);
    }
}
