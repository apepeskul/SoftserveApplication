<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>403 Access is denied</title>
    <meta charset = "utf-8">
    <link rel="stylesheet" type="text/css" href="static/css/error_styles.css">
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap.min.css">

    <script type="static/js/bootstrap.min.js"></script>
    <script type="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="span12">
            <div class="hero-unit center">
                <h1>Access is denied <small><font face="Tahoma" color="red">403 Forbidden</font></small></h1>
                <br />
                <p>You do not have permission to view this directory or page using the credentials that you supplied. <p>${message}.</p> Use your browsers <b>Back</b>
                    button to navigate to the page you have prevously come from.</p>
                <p><b>Or you could just press this button:</b></p>
                <a href="http://localhost:8080/" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> Take Me Home</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>