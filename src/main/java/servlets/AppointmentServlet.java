package servlets;

import DAOs.AppointmentDAO;
import DAOs.PatientDAO;
import Utils.SecurityRoles;
import beans.AppointmentBean;
import beans.PatientBean;
import beans.UserBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
/**
 * Servlet that manage information about Appointments
 */
//@WebServlet(name = "AppointmentServlet")
public class AppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        UserBean user = (UserBean) session.getAttribute("user");
        AppointmentDAO AppointmentDAO = new AppointmentDAO();
        AppointmentBean app = new AppointmentBean();
        PatientBean patientBean = new PatientBean();
        PatientDAO patientDAO = new PatientDAO();
        try {
            patientBean = patientDAO.findPatient(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            /**
             * Take the input date from the user and format it
             * */
            String examination = request.getParameter("examination");
            String DateString = request.getParameter("dateApp");
            Date date = Date.valueOf(DateString);
            if (action.equalsIgnoreCase("createAppointment")) {
                    //provided by the user-patient
                    app.setDate(date);
                    app.setKind_of_examination(examination);

                    //provided from the patient table
                    app.setUsername(patientBean.getUsername());
                    app.setAmka(patientBean.getAmka());
                    app.setAsfaleia(patientBean.getAsfaleia());
                    app.initalPartic_Doctors();
                    AppointmentDAO.insertNewAppointment(app);
            }
            if (action.equalsIgnoreCase("enterAvailableDays")) {

            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
            createDynPage(response, appointments, user);
        }
        if (action.equalsIgnoreCase("cancelAppointment")) {
            String DateString = request.getParameter("date");
            Date date = Date.valueOf(DateString);
            AppointmentDAO.deleteAppointment(date, user.getUsername(), user.getRole());
        }
        try {
            request.setAttribute("Appointments", AppointmentDAO.findAppointment(user));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a dynamic page which provide the Appointments to Doctors and Patients
     *
     * @param appointments The AppointmentBean list object that provided from the doPost() method
     * @param user         The UserBean object
     */
    public void createDynPage(HttpServletResponse response, List<AppointmentBean> appointments, UserBean user) throws IOException {

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
        out.println("<tr>");
        out.println("<th><h2> Date  </h2></th>");
        if (SecurityRoles.rolePatient.equals(user.getRole())) {
            out.println("<th><h2> - Examination   -    </h2</th>");
            out.println("<th><h2> Participation </h2></th>");
        }
        out.println("</tr>");
        out.println("</td>");
        out.println("<td>");
        //Loop the Appointments object and provide the date for both Doctors and Patients
        for (AppointmentBean appointment : appointments) {
            out.println("<tr style=\"text-align: center;\"><td>" + appointment.getDate() + "</td>");
            //If the user is Patient provide also the Kind of examination and the participation
            if (SecurityRoles.rolePatient.equals(user.getRole())) {
                out.println("<td>" + appointment.getKind_of_examination() + "</td>");
                out.println("<td>" + appointment.getParticipation() + "</td>");
            }
            out.println("<td><a href=\"appointment?action=cancelAppointment&date=" + appointment.getDate() + "\" role=\"button\">Delete</a></td>");
            out.println("</tr>");
        }
        out.println("</td>");
        out.println("</tr>");
        out.println("</tbody>");
        out.println(" </table>");
        out.println(" </br>");
        out.println(" </br>");
        out.println("</body></html>");

    }
}
