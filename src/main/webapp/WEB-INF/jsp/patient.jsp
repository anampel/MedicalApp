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
                        <th>Actions:</th>
                    </tr>
                    <tr>
                        <th>
                            <a class="btn btn-primary" href="appointment?action=findAppointment" role="button" aria-expanded="false" aria-controls="collapseExample">
                                View your appointments
                            </a>
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false" aria-controls="collapseExample">
                                Create an appointment
                            </a>
                        </th>
                        <td>
                            <div class="collapse" id="collapse1">
                                <div class="card-body">
                                    <form method="POST" action='${pageContext.request.contextPath}/appointment' name="enterform">
                                        <input type="hidden" name="action" value="createAppointment" />
                                        <div class="form-group">
                                            <label for="dateApp" class="text-info">date:</label><br>
                                            <input type="date" name="dateApp" id="dateApp" class="form-control" size="45" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="examination" class="text-info">examination:</label><br>
                                            <input type="text" name="examination" id="examination" class="form-control" size="45" required>
                                        </div>
                                        <br>
                                        <div class="form-group">
                                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Submit">
                                        </div>
                                    </form>
                                </div>
                            </div>
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