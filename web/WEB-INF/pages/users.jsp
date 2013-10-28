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

    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />

</head>
<body>

<div class="container">
    <div class="row">
        <div class="span8 offset2">

            <h1>Create new user</h1>
            <form:form method="post" action="add" commandName="user" class="form-horizontal">
            <div class="control-group">
                <form:label cssClass="control-label" path="login">Login:</form:label>
                <div class="controls">
                    <form:input path="login"/>
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

                <%-- <c:if test="${!empty roles}">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>Role</tr>
                    </thead>
                    <tbody>  --%>
                    <c:forEach items="${roles}" var="rolez">
                        <%--  <form:label path="role">${role.role}</form:label>  --%>
                              <form:radiobutton label="${rolez.role}" path="role" value="${rolez.role} "/>
                          <%-- <spring:message code="${role.role}"/>
                           <tr>
                               <td>${role.role}</td>
                           </tr>

                           </tbody>
                           </table>
                       </c:if>--%>
                    </c:forEach>
                    <div class="control-group">
                <div class="controls">
                    <input type="submit" value="Add User" class="btn btn-success"/>
                    </form:form>
                </div>
                        </div>

            <c:if test="${!empty users}">
                <h3>Users</h3>
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
    </div>

</body>
</html>