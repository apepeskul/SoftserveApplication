<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Order Managment System</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap-responsive.css" />
    <script src="/static/js/bootstrap.js"></script>
    <script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>

    <style>
        label.valid {
            width: 24px;
            height: 24px;
            background: url(/static/img/valid.png) center center no-repeat;
            display: inline-block;
            text-indent: -9999px;
        }
        label.error {
            font-weight: bold;
            color: red;
            padding: 2px 8px;
            margin-top: 2px;
        }
    </style>

    <style>
        .span9 > div
        {
            background-color:#eee;
            border: 1px solid #888;
            border-radius:3px;
        }
    </style>




</head>

<body>

<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Order Managment System</a>
            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="#"><i class="icon-home icon-white"></i> Home</a></li>
                    <li><a href="/">Administration</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Orders <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">New order</a></li>
                            <li class="divider"></li>
                            <li><a href="#">My orders</a></li>

                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Items <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/rest/item/new">New item</a></li>
                            <li class="divider"></li>
                            <li><a href="#">All items</a></li>

                        </ul>
                    </li>

                    <li class="divider-vertical"></li>
                </ul>
                <ul class="nav pull-right">
                    <li class="dropdown ">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown">Sign In <strong class="caret"></strong></a>
                        <div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
                            <form action="[YOUR ACTION]" method="post" accept-charset="UTF-8">
                                <input id="user_username" style="margin-bottom: 15px;" type="text" name="user[username]" size="30" placeholder="login:" />
                                <input id="user_password" style="margin-bottom: 15px;" type="password" name="user[password]" size="30" placeholder="password:" />
                                <input id="user_remember_me" style="float: left; margin-right: 10px;" type="checkbox" name="user[remember_me]" value="1" />
                                <label class="string optional" for="user_remember_me"> Remember me</label>

                                <input class="btn btn-primary" style="clear: left; width: 100%; height: 32px; font-size: 13px;" type="submit" name="commit" value="Sign In" />
                            </form>
                        </div>
                    </li>
                </ul>



            </div><!-- /.nav-collapse -->
        </div><!-- /.container -->
    </div><!-- /.navbar-inner -->
</div><!-- /.navbar -->

<div class="container" style="margin-top: 40px ">

    <div class="row-fluid">
        <div class="span12 offset2">


                    <h1>Add item</h1>

                    <form:form id="itemform"  method="post" action="/rest/item/add"  commandName="item" class="form-horizontal">

                    <div class="control-group">
                        <form:label cssClass="control-label" path="name">Name:</form:label>
                        <div  class="controls">
                            <form:input id="name" path="name"/>

                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="description">Description:</form:label>
                        <div class="controls">
                            <form:textarea id="description" path="description"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="quantity">Quantity:</form:label>
                        <div class="controls">
                            <form:input id="quantity" path="quantity"/>
                        </div>
                    </div>


                    <input style="margin-left: 200px" type="submit"  value="Add User" class="btn btn-success"/>

                    </form:form>
                </div>
            </div>
        </div>

<script type="text/javascript">
    $(document).ready(function(){
                $('form').each(function () {
                            $(this).validate(

                                    {
                                        rules: {
                                            name: {
                                                minlength: 2,
                                                required: true
                                            },
                                            description: {

                                                minlength: 8
                                            },
                                            quantity: {

                                                required: true

                                            }



                                        },
                                        highlight: function(element) {
                                            $(element).closest('.control-group').removeClass('success').addClass('error');
                                        },
                                        success: function(element) {
                                            element
                                                    .text('OK!').addClass('valid')
                                                    .closest('.control-group').removeClass('error').addClass('success');
                                        }

                                    })
                        }
                )
            }

    );
    // end document.ready
</script>
</body>
</html>