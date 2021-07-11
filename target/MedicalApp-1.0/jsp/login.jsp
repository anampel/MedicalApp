<%--
  Created by IntelliJ IDEA.
  Date: 27-May-21
  Time: 16:11 PM
  Login form
  Included in header page
  Includes --> Action to Patient Servlet
           --> mini form validation
           --> TODO comments
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="loginModal" class="modal" role="dialog">
    <div class="modal-dialog modal-lg" role="content">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">

                <h4 class="modal-title text-center text-info">Login </h4>

                <button type="button" class="close" data-dismiss="modal">&times</button>
            </div>
            <div class="modal-body">

                <form id="login-form" name="login-form" class="form" method="POST" onsubmit="return validateForm()" action="${pageContext.request.contextPath}/login">
                    <div class="form-group">
                        <label for="username" class="text-info">username:</label><br>
                        <input type="text" name="username" id="username" class="form-control" size="45" required>
                    </div>

                    <div class="form-group">
                        <label for="password" class="text-info">password:</label><br>
                        <input type="password" name="password" id="password" class="form-control" size="22" required>
                    </div>
                    <br>
                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="Submit">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

