<%--
  Created by IntelliJ IDEA.
  Date: 28-May-21
  Time: 11:50 PM
  Header page
  Includes --> login page
           --> navbar with links
           --> logo image
           --> TODO comments
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-md navbar-light bg-light fixed-top ">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#Navbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="Navbar">
<%--            TODO create these pages--%>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active"><a class="nav-link" href="index.jsp"><span class="fa fa-home fa-lg"></span>&nbsp Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><span class="fa fa-info fa-lg"></span>&nbsp About</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><span class="fa fa-address-card fa-lg"></span>&nbsp Contact</a></li>
            </ul>
            <span class="navbar-text">
                    <a data-toggle="modal" class="btn btn-light" data-target="#loginModal">
                        Login
                    </a>
            </span>
        </div>
    </div>
</nav>
<%--TODO should not provided if the user is in home (welcome) page.--%>
<%@include file="login.jsp"%>
<header class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col">
                <img class="rounded float-start img-fluid shadow-lg p-3 mb-5 bg-body rounded mx-auto d-block" src="images/logo.jpg">
            </div>
        </div>
    </div>
</header>
