<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Login</title>

    <meta charset = "utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap.min.css">

    <script type="static/js/bootstrap.min.js"></script>
    <script type="http://code.jquery.com/jquery-latest.js"></script>
    <style>
        .well {
            margin: auto;
            height: 40%;
            position: absolute;
            top: 0; left: 0; bottom: 0; right: 0;
        }
    </style>
</head>

<body>
<div class="container" >
    <div class="row">
        <div class="span4 offset4 well">
            <legend>Please Sign In</legend>
            <div class="alert alert-error " hidden="true">
                <a class="close" data-dismiss="alert" href="#">x</a>Incorrect Username or Password!
            </div>
            <form method="post" action="/j_spring_security_check" accept-charset="UTF-8">
                <input type="text" class="span4" id="j_username" name="j_username" placeholder="Username">
                <input type="password" class="span4" id="j_password" name="j_password" placeholder="Password">
                <label class="checkbox">
                    <input type="checkbox" name="remember" value="1"> Remember Me
                </label>
                <button type="submit" name="commit" class="btn btn-info btn-block">Sign in</button>
            </form>
        </div>
    </div>
</div>
<%--<script>--%>
<%--$(function(){--%>
<%--$("#hd").removeClass('hidden');--%>
<%--});--%>
<%--</script>--%>
</body>
</html>