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
                    <th>AMKA</th>
                    <td>${patient.amka}</td>
                </tr>
                <tr>
                    <th>Insurance</th>
                    <td>${patient.asfaleia}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <br>
    <br>
    <div class="row justify-content-center">
        <div class="col-6 col-md-4">
            <table class="table">
                <thead>
                <tr>
                    <h5 class="h5">Appointments</h5><br>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>Action</th>
                        <td><a href="appointment?action=findAppointment">View your appointments</a></td>
                        <td><a href="#date1">Enter new available day for appointment</a></td>
                        <td><a href="#date2" >Cancel an appointment</a></td>
                    </tr>
                    <tr>
                        </br>
                        </br>
                        <td>&nbsp;&nbsp;&nbsp;</td>
                        <td>&nbsp;&nbsp;&nbsp;</td>
                        <td>
                            <form method="POST" action='${pageContext.request.contextPath}/appointment' name="enterform">
                                <input type="hidden" name="action" value="enterAvailableDays" />
                                <label for="date1">Date:</label>
                                <input type="date" id="date1" name="date1"></br>
                                <input type="submit" value="Submit">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action='${pageContext.request.contextPath}/appointment' name="cancelform">
                                <input type="hidden" name="action" value="cancelAppointment" />
                                <label for="date2">Date:</label>
                                <input type="date" id="date2" name="date2"></br>
                                <input type="submit" value="Submit">
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>