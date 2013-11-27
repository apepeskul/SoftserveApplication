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
    <script src="/static/js/filltable.js"></script>
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
                    <li><a href="#">Administration</a></li>
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

<div class="container-fluid" style="margin-top: 40px ">

    <div class="row">
        <div class="span9 offset1">
            <div class="row" style="border-bottom: inactiveborder">
            <div class="span6">
            <h1>Create new user</h1>

            <form:form id="userform"  method="post" action="add"  commandName="user" class="form-horizontal">

            <div class="control-group inline">
                <form:label cssClass="control-label" path="login">Login:</form:label>
                <div  class="controls">
                    <form:input id="login" path="login"/>

                </div>
                </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="firstName">First Name:</form:label>
                <div class="controls">
                    <form:input id="firstName" path="firstName"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="lastName">Last Name:</form:label>
                <div class="controls">
                    <form:input id="lastName" path="lastName"/>
                </div>
                </div>
             <div class="control-group">
                    <form:label cssClass="control-label" path="password">Password:</form:label>
                    <div class="controls">
                        <form:password id="password" path="password"/>
                    </div>
            </div>

                <div class="control-group">
                    <label for="confirmPassword" class="control-label">Confirm password:</label>
                    <div class="controls">

                        <input type="password" id="confirmPassword" name="confirmPassword"/>
                    </div>
                </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="email">Email:</form:label>
                <div class="controls">
                    <form:input id="email" path="email"/>
                </div>
            </div>
                <div class="control-group">
                    <form:label cssClass="control-label" path="region">Region:</form:label>
                <div class="controls">
                <form:select path="region">
                    <form:option value="North"></form:option>
                    <form:option value="West"></form:option>
                    <form:option value="South"></form:option>
                    <form:option value="East"></form:option>
                </form:select>
            </div>
            </div>
             <br>


            </div>
            <div class="span2">
                <div class="control-group">
                    <br>
                    <br>
                    <br>
                    <label class="label">Roles:</label>
                    <br>

                    <div class="controls">
                        <c:forEach items="${roles}" var="forrole">


                        <input type="radio" name="rid" value="${forrole.id}"> ${forrole.description}</>
                    <br>
                    </c:forEach>
                    <hr>
                    </div>

                </div>
                </div>

        </div>
            <div class="row" style="border-top: InactiveBorder; border-bottom: inactiveborder">
                <div class="pagination-centered">
                <div class="control-group">
                    <div class="controls">
                        <input type="submit"  value="Add User" class="btn btn-success"/>
                        </form:form>
                    </div>
                </div>
                </div>
            </div>
        <div class="row"> <div class="modal fade hide" style="width: 740px" id="myModal"  tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="false" >
            <div class="modal-dialog" >
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Edit user</h4>
                    </div>
                    <div class="modal-body" id="body" style="max-height: 600px">
                        <div class="row">

                            <div class="span5" >
                                <form:form  method="post" action="add"  commandName="user" class="form-horizontal" >

                                <div class="control-group">
                                    <form:hidden id="modalId" path="id"/>
                                    <form:label cssClass="control-label" path="login">Login:</form:label>
                                    <div class="controls">
                                        <form:input id="modalLogin" path="login"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="firstName">First Name:</form:label>
                                    <div class="controls">
                                        <form:input id="modalFirstName" path="firstName"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="lastName">Last Name:</form:label>
                                    <div class="controls">
                                        <form:input id="modalLastName" path="lastName"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="password">Password:</form:label>
                                    <div class="controls">
                                        <form:password id="modalpassword" path="password"/>
                                    </div>
                                </div>

                                    <div class="control-group">
                                        <label for="confirmpassword" class="control-label">Confirm password:</label>
                                        <div class="controls">

                                            <input type="password" id="modalconfirmpassword" name="modalconfirmpassword"/>
                                        </div>
                                    </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="email">Email:</form:label>
                                    <div class="controls">
                                        <form:input id="modalEmail" path="email"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="region">Region:</form:label>
                                    <div class="controls">
                                        <form:select id="modalRegion" path="region">
                                            <form:option value="North"></form:option>
                                            <form:option value="West"></form:option>
                                            <form:option value="South"></form:option>
                                            <form:option value="East"></form:option>
                                        </form:select>
                                    </div>
                                </div>

                            </div>
                            <div class="span2">
                                <div class="control-group">
                                    <br>
                                    <br>
                                    <br>
                                    <label class="label">Roles:</label>
                                    <br>
                                    <div class="controls">
                                        <c:forEach items="${roles}" var="forrole">


                                        <input type="radio" id="modalRole${forrole.id}" name="rid" value="${forrole.id}" >${forrole.description}</>
                                    <br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <input type="submit" id="modalSubmit" value="Save changes" class="btn btn-primary"/>
                        </form:form>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        </div>
        </div>
        </div>
    <div class="span6" style="-webkit-box-sizing: border-box">



        <c:if test="${!empty users}">
        <h3>Users</h3>
        <br>
        <div>
            <form class="form-search text-center" method="get" action="search">
                <div class="input-append">

                    <input type="search" id="search_input" class="span2 search-query" name="q"  autocomplete="off" placeholder="Enter user's login" tabindex="1">
                    <button type="submit" class="btn"><i class="icon-search"></i> </button>
                </div>
            </form>
            <div id="tableDiv">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Login</th>
                        <th>Email</th>
                        <th>Region</th>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.lastName}, ${user.firstName}</td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>${user.region}</td>
                            <td>
                                <form action="delete/${user.id}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>
                            </td>
                            <td>
                                <button class="btn-mini btn-warning" id="editBtn${user.id}" value="${user.id}" data-toggle="modal">Edit</button>

                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
            </div>
        </div>
    </div>
    </div>


</div>

<script type="text/javascript">

    $('#search_input').keyup(function (){
                if ($('#search_input').val().length>1){
        $.getJSON("/api/users", {b:$('#search_input').val()}, function(users){
            fillTable(users);
        })

    }
    }
    )

</script>
<script type="text/javascript">
    $("[id^=editBtn]").click(function(){
        $.getJSON("users/"+$(this).val(), function(user){

           //$("[id^=modalRole]").removeAttr('checked');
            $('#myModal').modal({
                keyboard: false
        })

            $('#modalLogin').val(user.login);
            $('#modalFirstName').val(user.firstName);
            $('#modalLastName').val(user.lastName);
            $('#modalEmail').val(user.email);
            $('#modalPassword').val(user.password);
            $('#modalRegion').val(user.region);
            $('#modalId').val(user.id);

            $("#modalRole"+user.role.id).prop('checked', true);

        }
        );
        $('#myModal').on('hidden', function() {
            $("[id^=modalRole]").prop('checked', false);
            $(this).removeData('modal');

        });


    })
</script>
<script type="text/javascript">
    $(document).ready(function(){
        $('form').each(function () {
            $(this).validate(

                {
                    rules: {
                        login: {
                            minlength: 2,
                            required: true
                        },
                        email: {
                            required: true,
                            email: true
                        },
                        lastName: {
                            minlength: 2,
                            required: true

                        },
                        firstName: {
                            minlength: 2,
                            required: true

                        },

                        password:  {
                            minlength:2,
                            required: true
                        },

                        confirmPassword :{

                            equalTo: password


                    },
                    modalpassword:  {
                        minlength:2,
                        required: true
                    },

                    modalconfirmpassword :{

                        equalTo: modalpassword
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