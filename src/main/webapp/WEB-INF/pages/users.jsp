<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="select" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Order Management System</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css">--%>
    <link rel="stylesheet" type="text/css" href="/static/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap-responsive.css" />

    <script src="/static/js/bootstrap.js"></script>
    <script src="http://ethaizone.github.io/Bootstrap-Confirmation/assets/js/bootstrap-tooltip.js"></script>
    <script src="/static/js/tooltip.js"></script>
    <script src="/static/js/bootstrap-confirmation.js"></script>
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.js"></script>
    <script type="text/javascript" src="static/js/DT_bootstrap.js"></script>


    <script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
    <script src="http://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery.dataTables.columnFilter.js"></script>


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
        .span10
        {
            background-color:#eee;
            border: 1px solid #888;
            border-radius:3px;
        }
    </style>

  <style>
      input.text_filter
      {
        max-width: 160px;
      }
  </style>
    <style>
        select.search_init.select_filter
        {
            max-width: 160px;
        }
    </style>
    <%--<style>
        th{
            max-width: 165px;
        }
    </style>--%>


</head>

<body>

<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Order Management System</a>
            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="#"><i class="icon-home icon-white"></i> Home</a></li>
                    <li class="active"><a href="#"><i class="icon-user icon white"></i> Administration</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-shopping-cart icon white"></i> Orders <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/order">New order</a></li>
                            <li class="divider"></li>
                            <li><a href="/orders"><i class="icon-shopping-cart icon white"></i> My orders</a></li>

                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-gift icon white"></i> Items <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/item">New item</a></li>
                            <li class="divider"></li>
                            <li><a href="/items">All items</a></li>

                        </ul>
                    </li>

                    <li class="divider-vertical"></li>
                </ul>
                <ul class="nav pull-right">
                    <li class="dropdown ">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown" style="color: #ffdead"> <sec:authentication  property="name" /> <strong class="caret"></strong></a>
                        <div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
                            <form action="/logout" method="post" accept-charset="UTF-8">

                                <input class="btn btn-primary" style="clear: left; width: 100%; height: 32px; font-size: 13px;" type="submit" name="commit" value="Log out" />
                            </form>
                        </div>
                    </li>
                </ul>



            </div><!-- /.nav-collapse -->
        </div><!-- /.container -->
    </div><!-- /.navbar-inner -->
</div><!-- /.navbar -->



<div class="container" style="margin-top: 40px ">

    <div class="row">
        <div class="span10 offset1">
            <div class="row" style="border-bottom: inactiveborder">
                <div class="span6">
                    <h1 align="center">Create new user</h1>

                    <form:form id="userform"  method="post" action="add"  commandName="user" class="form-horizontal">

                    <div class="control-group inline">
                        <form:label cssClass="control-label" path="login" data-toggle="tooltip" data-placement="top" title="" data-original-title="Type login off new user">Login:</form:label>
                        <div  class="controls">
                            <form:input id="login" path="login"/>

                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="firstName" data-toggle="tooltip" data-placement="top" title="" data-original-title="Type firstname">First Name:</form:label>
                        <div class="controls">
                            <form:input id="firstName" path="firstName"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="lastName" data-toggle="tooltip" data-placement="top" title="" data-original-title="Type lastname">Last Name:</form:label>
                        <div class="controls">
                            <form:input id="lastName" path="lastName"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="password" data-toggle="tooltip" data-placement="top" title="" data-original-title="Type password">Password:</form:label>
                        <div class="controls">
                            <form:password id="password" path="password"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="confirmPassword" class="control-label" data-toggle="tooltip" data-placement="top" title="" data-original-title="Confirm password">Confirm password:</label>
                        <div class="controls">

                            <input type="password" id="confirmPassword" name="confirmPassword"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="email" data-toggle="tooltip" data-placement="top" title="" data-original-title="Type e-mail">Email:</form:label>
                        <div class="controls">
                            <form:input id="email" path="email"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label cssClass="control-label" path="region" data-toggle="tooltip" data-placement="top" title="" data-original-title="Select region">Region:</form:label>
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
                        <label class="label" data-toggle="tooltip" data-placement="top" title="" data-original-title="Choose role of new user">Roles:</label>
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
                        <input type="submit"  value="Add User" class="btn btn-success" data-toggle="tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Click to create a new user"/>

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
                        <div class="row-fluid">

                            <div class="span8" style="margin-top: 30px" >
                                <form:form  method="post" action="update"  commandName="user" class="form-horizontal" >

                                <div class="control-group">
                                    <form:hidden id="modalId" path="id"/>
                                    <form:label cssClass="control-label" path="login" data-toggle="tooltip" data-placement="top" title="" data-original-title="Retype if you want to change login">Login:</form:label>
                                    <div class="controls">
                                        <form:input id="modalLogin" path="login"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="firstName" data-toggle="tooltip" data-placement="top" title="" data-original-title="Retype if you want to change firstname">First Name:</form:label>
                                    <div class="controls">
                                        <form:input id="modalFirstName" path="firstName"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="lastName" data-toggle="tooltip" data-placement="top" title="" data-original-title="Retype if you want to change lastname">Last Name:</form:label>
                                    <div class="controls">
                                        <form:input id="modalLastName" path="lastName"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="password" data-toggle="tooltip" data-placement="top" title="" data-original-title="Retype if you want to change password">Password:</form:label>
                                    <div class="controls">
                                        <form:password id="modalPassword" path="password"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label for="modalConfirmPassword" class="control-label" data-toggle="tooltip" data-placement="top" title="" data-original-title="Confirm password">Confirm password:</label>
                                    <div class="controls">

                                        <input type="password" id="modalConfirmPassword" name="modalConfirmPassword"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="email" data-toggle="tooltip" data-placement="top" title="" data-original-title="Retype if you want to change e-mail">Email:</form:label>
                                    <div class="controls">
                                        <form:input id="modalEmail" path="email"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <form:label cssClass="control-label" path="region" data-toggle="tooltip" data-placement="top" title="" data-original-title="Select region">Region:</form:label>
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
                            <div class="span3 offset1">
                                <div class="control-group">
                                    <br>
                                    <br>
                                    <br>
                                    <label class="label" data-toggle="tooltip" data-placement="top" title="" data-original-title="Choose role">Roles:</label>
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

</div>


<div class="row">

      <div class="span12" style="margin-top: 40px">

        <h2 align="center">All users</h2>
        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="userstable">
            <thead>
            <tr>
                <th>Login</th>
                <th>Name</th>
                <th>Role</th>
                <th>Email</th>
                <th>Region</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
            <tfoot>
            <tr>
                <th>Login</th>
                <th>Name</th>
                <th>Role</th>
                <th>Email</th>
                <th>Region</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            </tfoot>
        </table>

</div>
</div>

</div>
<script type="text/javascript">
    $(document).ready(function() {
         $('#userstable').dataTable( {
            "sAjaxSource": "/datatables" ,
             "sAjaxDataProp": "",
             "aoColumns": [
                 { "mDataProp": "login" },

                 {   "mData": "lastName"
                           },
                 {   "sDefaultContent": "",
                     "mDataProp": "role.description" },
                 { "mDataProp": "email" },
                 { "mDataProp": "region" },
                 {   "sDefaultContent": "",
                     "fnRender": function(o) { return '<input type="button" id="delete/'+o.aData["id"]+ '" class="btn btn-danger btn-mini"  value="Delete" data-toggle="confirmation" data-href="/delete/'+o.aData["id"]+'" />'}
                 },                                                                                                                                                          //
                 {   "sDefaultContent": "",
                     "fnRender": function(o) { return '<button class="btn-mini btn-warning" id="editBtn' + o.aData["id"] + '" value="'+o.aData["id"]+'" data-toggle="modal">Edit</button>'}
                 }
             ],



            "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
            "sPaginationType": "bootstrap",
        "oLanguage": {
                "sLengthMenu": "_MENU_ records per page"
            },
            "iDisplayLength": 5,
            "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
            "bProcessing": true,
             "fnDrawCallback": function() {
                 $("[id^=delete]").confirmation({singleton: true, popout:true});
             }
       } )
                 .columnFilter({
                     aoColumns: [
                         {type: "text"},
                         {type: "text"},
                         {type: "select", values : ['Admin', 'Customer', 'Merchandiser', 'Superviser']},
                         {type: "text"},
                         {type:"select", values : ['North', 'South', 'West', 'East']},
                         null,
                         null]});


    } );

</script>

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
    $(document).on("click", "[id^=editBtn]", function() {

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
                    $('#modalConfirmPassword').val(user.password);

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
                                            modalPassword:  {
                                                minlength:2,
                                                required: true
                                            },

                                            modalConfirmPassword :{

                                                equalTo: modalPassword
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