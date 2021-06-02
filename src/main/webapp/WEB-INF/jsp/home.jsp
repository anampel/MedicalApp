<%--
  Created by IntelliJ IDEA.
  Date: 28-May-21
  Time: 9:10 PM
  Welcome page
  Includes --> Bootstrap 4
           --> PatientBean's parameters
           --> AppointmentBean's parameters
           --> header and footer pages
           --> logout activity
           --> TODO comments
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
            <h3 class="h3">${patient.name} ${patient.surname}</h3> <br>
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
                        <h5 class="h5">Appointment History</h5><br>
                    </tr>
                </thead>
                <tbody>
                <tr>
                    <th>Date</th>
                    <td>${appointment.date}</td>
                </tr>
                <tr>
                    <th>Examination</th>
                    <td>${appointment.kind_of_examination}</td>
                </tr>
                <tr>
                    <th>Participation</th>
                    <td>${appointment.participation}</td>
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
