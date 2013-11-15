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

    <script type="text/javascript">
        function fillTable(ajaxArr){

            var div = document.getElementById("tableDiv");
            div.innerHTML="";

            var table = document.createElement("table");
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
            table.appendChild(row);
            table.className="table table-bordered table-striped table-hover";
            div.appendChild(table);

            for (var i =0; i <ajaxArr.length;i++){
//                 alert(ajaxArr[i].lastName + ", " +  ajaxArr[i].firstName);
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
                table.appendChild(rows);
            }
        }



    </script>





</head>
<body>

<div class="container">
    <div class="row">
        <div class="span8 offset2">

            <h1>Create new user</h1>
            <form:form method="post" action="add"  commandName="user" class="form-horizontal">

            <div class="control-group">
                <form:label cssClass="control-label" path="login">Login:</form:label>
                <div class="controls">
                    <form:input path="login"/>
                </div>
                </div>
            <br>
            <div class="control-group">
                <form:label cssClass="control-label" path="firstName">First Name:</form:label>
                <div class="controls">
                    <form:input path="firstName"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="lastName">Last Name:</form:label>
                <div class="controls">
                    <form:input path="lastName"/>
                </div>
                </div>
                <br>
            <div class="control-group">
                    <form:label cssClass="control-label" path="password">Password:</form:label>
                    <div class="controls">
                        <form:input path="password"/>
                    </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="email">Email:</form:label>
                <div class="controls">
                    <form:input path="email"/>
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
                   <div class="control-group">
                <div class="controls">
                    <input type="submit" value="Add User" class="btn btn-success"/>

                </div>
                        </div>
            </form:form>
                <div class="control-group">
                    <div class="controls">
                <c:forEach items="${roles}" var="forrole">

                    <form:radiobutton label="${forrole.description}" path="temprole.id"  value="${forrole.id}"/>

                </c:forEach>
                    </div>
                </div>
                <br>

            <form class="form-search text-center" method="get" action="search">
                <div class="input-append">

                    <input type="search" id="search_input" class="span2 search-query" name="q"  autocomplete="off" placeholder="Enter user's login" tabindex="1">
                    <button type="submit" class="btn"><i class="icon-search"></i> </button>
                </div>
            </form>


            <c:if test="${!empty users}">
                <h3>Users</h3>
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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
                </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#search_input').keyup(function (){


        $.getJSON("/api/users", {b:$('#search_input').val()}, function(users){
            fillTable(users);
        })


    })

</script>
</body>
</html>