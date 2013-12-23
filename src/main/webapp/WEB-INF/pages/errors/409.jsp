<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>409 Conflict</title>
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
                <h1>Conflict <small><font face="Tahoma" color="red">409 Conflict</font></small></h1>
                <br />
                <p>The server encountered an internal error or misconfigurationand was unable to complete your request. <b> User with this ${errorCause} have already exists. </b>
                    Please contact the server administrator, and inform him of the time the error occurred, and anything you might have done that may have caused the error. </p>
                    Use your browsers <b>Back</b> button to navigate to the page you have prevously come from</p>
                <p><b>Or you could just press this button:</b></p>
                <a href="http://localhost:8080/" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> Take Me Home</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>