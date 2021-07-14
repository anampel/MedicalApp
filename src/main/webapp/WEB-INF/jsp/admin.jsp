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
        <div class="col-6 col-md-6">
            <%--    TODO find if the user is patient/admin/doctor and provide the corresponding fields--%>
            <table class="table">
                <tbody>
                <tr>
                    <th>ID number</th>
                    <td>${admin.id}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-6 col-md-6">
            <table class="table">
                <tbody>
                <tr>
                    <th>Actions:</th>
                </tr>
                <tr>
                    <th>
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapse" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Insert New Doctor
                        </a>
                    </th>
                    <td>
                        <div class="collapse" id="collapse">
                            <div class="card-body">
                                <form id="insDoc" name="insDoc" class="form" method="POST" action="${pageContext.request.contextPath}/admin">
                                    <input type="hidden" name="action" value="insertDoctor" />
                            <div class="form-group">
                                <label for="usernameDoc" class="text-info">username:</label><br>
                                <input type="text" name="usernameDoc" id="usernameDoc" class="form-control" size="45" required>
                            </div>
                            <div class="form-group">
                                <label for="nameDoc" class="text-info">name:</label><br>
                                <input type="text" name="nameDoc" id="nameDoc" class="form-control" size="45" required>
                            </div>
                            <div class="form-group">
                                <label for="surnameDoc" class="text-info">surname:</label><br>
                                <input type="text" name="surnameDoc" id="surnameDoc" class="form-control" size="45" required>
                            </div>
                            <div class="form-group">
                                <label for="phoneDoc" class="text-info">phone:</label><br>
                                <input type="text" name="phoneDoc" id="phoneDoc" class="form-control" size="45" required>
                            </div>
                            <div class="form-group">
                                <label for="specialtyDoc" class="text-info">specialty:</label><br>
                                <input type="text" name="specialtyDoc" id="specialtyDoc" class="form-control" size="45" required>
                            </div>
                            <div class="form-group">
                                <label for="passwordDoc" class="text-info">password:</label><br>
                                <input type="password" name="passwordDoc" id="passwordDoc" class="form-control" size="22" required>
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
                <tr>
                    <th>
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Insert New Patient
                        </a>
                    </th>
                    <td>
                        <div class="collapse" id="collapse1">
                            <div class="card-body">
                                <form id="insPat" name="insPat" class="form" method="POST" action="${pageContext.request.contextPath}/admin">
                                    <input type="hidden" name="action" value="insertPatient" />
                                    <div class="form-group">
                                        <label for="usernamePat" class="text-info">username:</label><br>
                                        <input type="text" name="usernamePat" id="usernamePat" class="form-control" size="45" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="namePat" class="text-info">name:</label><br>
                                        <input type="text" name="namePat" id="namePat" class="form-control" size="45" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="surnamePat" class="text-info">surname:</label><br>
                                        <input type="text" name="surnamePat" id="surnamePat" class="form-control" size="45" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="phonePat" class="text-info">phone:</label><br>
                                        <input type="text" name="phonePat" id="phonePat" class="form-control" size="45" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="amkaPat" class="text-info">amka:</label><br>
                                        <input type="text" name="amkaPat" id="amkaPat" class="form-control" size="45" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="asfaleiaPat" class="text-info">insurance:</label><br>
                                        <input type="text" name="asfaleiaPat" id="asfaleiaPat" class="form-control" size="45" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="passwordPat" class="text-info">password:</label><br>
                                        <input type="password" name="passwordPat" id="passwordPat" class="form-control" size="22" required>
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
                <tr>
                    <th>
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Delete Doctor
                        </a>
                    </th>
                    <td>
                        <div class="collapse" id="collapse2">
                            <div class="card-body">
                                <form id="delDoc" name="insPat" class="form" method="POST" action="${pageContext.request.contextPath}/admin">
                                    <input type="hidden" name="action" value="deleteDoctor" />
                                    <div class="form-group">
                                        <label for="delUsernameDoc" class="text-info">Submit username to delete:</label><br>
                                        <input type="text" name="delUsernameDoc" id="delUsernameDoc" class="form-control" size="45" required>
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
                <tr>
                    <th>
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapse3" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Delete Patient
                        </a>
                    </th>
                    <td>
                        <div class="collapse" id="collapse3">
                            <div class="card-body">
                                <form id="delPat" name="insPat" class="form" method="POST" action="${pageContext.request.contextPath}/admin">
                                    <input type="hidden" name="action" value="deletePatient" />
                                    <div class="form-group">
                                        <label for="delUsernamePat" class="text-info">Submit username to delete:</label><br>
                                        <input type="text" name="delUsernamePat" id="delUsernamePat" class="form-control" size="45" required>
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