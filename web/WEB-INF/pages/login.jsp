<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap-responsive.css" />
    <script src="/static/js/bootstrap.js"></script>
</head>

<body>
If you haven't users in DB you'll use default values: admin admin
<div class="divider-vertical" style="margin-bottom: 50px"></div>
        <form action="/j_spring_security_check" style="display:block; margin:auto; width: 300px; vertical-align: middle" method="post" accept-charset="UTF-8">
            <input id="j_username" style="margin-bottom: 15px;" type="text" name="j_username" size="30" placeholder="login:" />
            <br>
            <input id="j_password" style="margin-bottom: 15px;" type="password" name="j_password" size="30" placeholder="password:" />
            <br>
            <input class="btn btn-primary" style="margin:auto; clear: left; width: 100px; height: 32px; font-size: 13px;" type="submit" name="commit" value="Sign In" />
        </form>
</body>
</html>