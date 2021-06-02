<%--
  Created by IntelliJ IDEA.
  Date: 28-May-21
  Time: 10:17 PM
  Main page
  Includes --> Bootstrap 4
           --> header and footer pages
           --> mini form validation
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page contentType="text/html;charset=UTF-8"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Medical Appointments</title>
    <%@include file="jsp/bootstrap.jsp"%>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js">

    </script>
</head>

<body>
<%@include file="jsp/header.jsp"%>
<div class="container">
    <div>
        <h3 class="h3">Medical Appointments</h3>
    </div>
    <div class="form-content">
        .<br>
        .<br>
        .<br>
        .<br>
        .<br>
        .<br>
        .<br>
        .<br>
        Content of the index page loading.....
        .<br>
        .<br>
        .<br>
        .<br>
        .<br>
        .<br>
        .<br>
        .<br>
    </div>
</div>
<%@include file="jsp/footer.jsp"%>

</body>
<script type="text/javascript">
    function validateForm() {
        var x = document.forms["login-form"]["username"].value;
        if (length(x) > 45) {
            alert("Invalid username");
            return false;
        }
    }
</script>
</html>