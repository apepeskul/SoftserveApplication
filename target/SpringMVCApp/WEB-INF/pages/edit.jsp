<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: APepeskul
  Date: 18.11.13
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=0.5">
</head>
<body>
<div class="row">

        <div class="span2">
                        <form:form id="userform" method="post" action="save"  commandName="edituser" class="form-horizontal">

                        <div class="control-group">
                            <form:label cssClass="control-label" path="login">Login:</form:label>
                            <div class="controls">
                                <form:input path="login"/>
                            </div>
                        </div>
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
                        <div class="control-group">
                            <form:label cssClass="control-label" path="password">Password:</form:label>
                            <div class="controls">
                                <form:password path="password"/>
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
                            <%--<div class="control-group">
                                <div class="controls">
                                    <input type="submit" form="userform" value="Add User" class="btn btn-success"/>

                                </div>--%>
                        </form:form>
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

                                <form:radiobutton label="${forrole.description}" path="temprole.id"  value="${forrole.id}"/>

                            </c:forEach>
                        </div>
                        </div>
                    </div>
        </div>

                        <%--<button type="button" data-toggle="modal" data-target="#myModal">Launch modal</button>--%>

</body>
</html>