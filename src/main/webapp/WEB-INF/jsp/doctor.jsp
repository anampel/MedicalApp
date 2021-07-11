<%--
  Created by IntelliJ IDEA.
  User: Anampel
  Date: 04-Jul-21
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <%@include file="bootstrap.jsp"%>
</head>
<body>
<%@include file="header.jsp"%>
<div style="text-align: center" class="container">
    <div class="row justify-content-center">
        <div class="col">
            <h1 class="h1">Welcome to Medical Appointments Application</h1> <br>
            <h3 class="h3">${user.name} ${user.surname}</h3> <br>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-6 col-md-4">
            <%--    TODO find if the user is patient/admin/doctor and provide the corresponding fields--%>
            <table class="table">
                <tbody>
                <tr>
                    <th>Specialty</th>
                    <td>${doctor.specialty}</td>
                </tr>
                <tr>
                    <th>Actions:</th>
                </tr>
<%--                <td><a href="appointment?action=findAppointment">View your appointments</a></td>--%>
<%--                <td><a href="#date1">Enter new available day for appointment</a></td>--%>
<%--                <td><a href="#date2" >Cancel an appointment</a></td>--%>
                <tr>
                    <th>
                        <a class="btn btn-primary" href="appointment?action=findAppointment" role="button" aria-expanded="false" aria-controls="collapseExample">
                            View your appointments
                        </a>
                    </th>
                    <td>
                        <div class="collapse" id="collapse">
                            <div class="card-body">
<%--                                <form method="POST" action='${pageContext.request.contextPath}/appointment' name="enterform">--%>
<%--                                    <input type="hidden" name="action" value="enterAvailableDays" />--%>
<%--                                    <label for="date1">Date:</label>--%>
<%--                                    <input type="date" id="date1" name="date1"></br>--%>
<%--                                    <input type="submit" value="Submit">--%>
<%--                                </form>--%>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Enter new available day for appointment
                        </a>
                    </th>
                    <td>
                        <div class="collapse" id="collapse1">
                            <div class="card-body">
                                <form method="POST" action='${pageContext.request.contextPath}/appointment' name="enterform">
                                    <input type="hidden" name="action" value="enterAvailableDays" />
                                    <label for="date1">Date:</label>
                                    <input type="date" id="date1" name="date1"></br>
                                    <input type="submit" value="Submit">
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Cancel an appointment
                        </a>
                    </th>
                    <td>
                        <div class="collapse" id="collapse2">
                            <div class="card-body">
                                <form method="POST" action='${pageContext.request.contextPath}/appointment' name="cancelform">
                                    <input type="hidden" name="action" value="cancelAppointment" />
                                    <label for="date2">Date:</label>
                                    <input type="date" id="date2" name="date2"></br>
                                    <input type="submit" value="Submit">
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <br>
    <br>
    <div class="row">
        <div class="col">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>