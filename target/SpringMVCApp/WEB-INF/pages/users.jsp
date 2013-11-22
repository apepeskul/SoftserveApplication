<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Spring MVC Application</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
    <script src="/css/bootstrap.js"></script>
    <script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
     <style>table{
         table-layout: fixed;
     }</style>
    <style>
        label.valid {
            width: 24px;
            height: 24px;
            background: url(/css/valid.png) center center no-repeat;
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
    <script type="text/javascript">
        function fillTable(ajaxArr){

            var div = document.getElementById("tableDiv");
            div.innerHTML="";

            var table = document.createElement("table");
            var head = document.createElement("thead");
            var row = document.createElement("tr");
            var cell1 = document.createElement("th");
            var cell2 = document.createElement("th");
            var cell3 = document.createElement("th");
            var cell4 = document.createElement("th");
            var cell5 = document.createElement("th");
            cell1.appendChild(document.createTextNode("Name"));
            cell2.appendChild(document.createTextNode("Login"));
            cell3.appendChild(document.createTextNode("Email"));
            cell4.appendChild(document.createTextNode("Region"));
            cell5.appendChild(document.createTextNode(""));
            row.appendChild(cell1);
            row.appendChild(cell2);
            row.appendChild(cell3);
            row.appendChild(cell4);
            row.appendChild(cell5);
            head.appendChild(row);
            table.appendChild(head);
            table.className="table table-bordered table-striped table-hover";
            div.appendChild(table);

            for (var i =0; i <ajaxArr.length;i++){
//                 alert(ajaxArr[i].lastName + ", " +  ajaxArr[i].firstName);
                var body = document.createElement("tbody");
                var rows = document.createElement("tr");
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                var td4 = document.createElement("td");
                var td5 = document.createElement("td");
                td1.appendChild(document.createTextNode(ajaxArr[i].lastName + ", " +  ajaxArr[i].firstName));
                td2.appendChild(document.createTextNode(ajaxArr[i].login));
                td3.appendChild(document.createTextNode(ajaxArr[i].email));
                td4.appendChild(document.createTextNode(ajaxArr[i].region));
                td5.innerHTML = '<form action="delete/'+ajaxArr[i].id+'" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>' ;
                rows.appendChild(td1);
                rows.appendChild(td2);
                rows.appendChild(td3);
                rows.appendChild(td4);
                rows.appendChild(td5);
                body.appendChild(rows);
                table.appendChild(body);
            }
        }



    </script>





</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="span9 offset1">
            <div class="row">
            <div class="span6">
            <h1>Create new user</h1>
            <form:form id="userform" method="post" action="add"  commandName="user" class="form-horizontal">

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
                </form:form>
                    <%--<button type="button" data-toggle="modal" data-target="#myModal">Launch modal</button>--%>





                </div>
                </div>

        </div>
            <div class="row">
                <div class="pagination-centered">
                <div class="control-group">
                    <div class="controls">
                        <input type="submit" form="userform" value="Add User" class="btn btn-success"/>

                    </div>
                </div>
                </div>
            </div>
        <div class="row"> <div class="modal fade hide" style="width: 720px" id="myModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="false" >
            <div class="modal-dialog" >
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Edit user</h4>
                    </div>
                    <div class="modal-body" id="body">
                        <div class="row">

                            <div class="span2">
                                <form:form id="editform" method="post" action="add"  commandName="user" class="form-horizontal">

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
                                        <form:password id="modalPassword" path="password"/>
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
                            <div class="span2 offset3">
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
            $('#modalEmail').val(user.Email);
            $('#modalRegion').val(user.region);
            $('#modalId').val(user.id);

            $("#modalRole"+user.role.id).prop('checked', true);






        }
        );
        $('#myModal').on('hidden', function() {
            $("[id^=modalRole]").prop('checked', false);
            $(this).removeData('modal');

        });

       /* $('#myModal').on('hide', function() {
          $("#modalRole"+user.role.id).prop('checked', false);
          $.modal.close();
        });*/




    })

</script>
<script type="text/javascript">
    $(document).ready(function(){

        $('#userform').validate(
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
                });
    }); // end document.ready
</script>
<%--<script>
    $('#modalSubmit').click(function(){
        $.post("/add")
    })
</script>--%>
</body>
</html>