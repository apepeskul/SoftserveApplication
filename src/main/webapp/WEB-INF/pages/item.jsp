<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Order Managment System</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
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
                    <li><a href="#"><i class="icon-home icon-white"></i> Home</a></li>
                    <li><a href="/"><i class="icon-user icon white"></i> Administration</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-shopping-cart icon white"></i> Orders <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/order">New order</a></li>
                            <li class="divider"></li>
                            <li><a href="/orders">My orders</a></li>

                        </ul>
                    </li>
                    <li class="dropdown active">
                        <a href="#" class="dropdown-toggle active" data-toggle="dropdown"><i class="icon-gift icon white"> </i> Items <b class="caret"></b></a>
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
                        <a class="dropdown-toggle" id="userlogin" href="#" data-toggle="dropdown" style="color: #ffdead"> <sec:authentication  property="name" /> <strong class="caret"></strong></a>
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
        <div class="span6">


            <h1 align="center">Add item</h1>

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


                <input style="margin-left: 200px" type="submit"  value="Add item" class="btn btn-success"/>

            </form:form>
        </div>
        <div class="span6">
            <h1 align="center">Add price</h1>

            <form:form id="priceform"  method="post" action="/rest/item/price/add"  commandName="price" class="form-horizontal">

                <div class="control-group">
                    <label for="items" class="control-label">Item:</label>
                    <div class="controls">
                        <select id="items" name="itm">
                            <c:forEach items="${items}" var="item">
                                <option value=${item.id}>${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            <div class="control-group">
                <label for="dimensions" class="control-label">Dimension:</label>
                <div class="controls">
            <select id="dimensions" name="dm" >

            <c:forEach items="${dimensions}" var="dimension">
                <option value=${dimension.dimensionId}>${dimension.name}</option>
            </c:forEach>
            </select>
                </div>
            </div>



            <div class="control-group">
                <form:label cssClass="control-label" path="price">Price:</form:label>
                <div class="controls">
                    <form:input id="price" path="price"/>
                    </div>
                </div>
                <input style="margin-left: 200px" type="submit"  value="Add price" class="btn btn-success"/>
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
                                                required: true,
                                                minlength: 8
                                            },
                                            quantity: {

                                                digits: true

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