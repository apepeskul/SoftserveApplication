<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="select" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Order Management System</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../static/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../../static/css/bootstrap-responsive.css" />

    <script src="/static/js/bootstrap.js"></script>

    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.js"></script>
    <script type="text/javascript" src="../../static/js/DT_bootstrap.js"></script>


    <script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
    <script src="http://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery.dataTables.columnFilter.js"></script>

    <!-- Стиль для отображения имени пользователя -->
    <style  type="text/css">
        .username
        {
            position: absolute;
            top: 0px;
            right: 100px;
            color: navajowhite;
        }
    </style>

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
        .span5 > div
        {
            background-color:#eee;
            border: 1px solid #888;
            border-radius:3px;
        }
    </style>




</head>

<body>

<!--Блок для отображения имени пользователя-->
<div class="username">
    <h4><%= request.getRemoteUser() %></h4>
</div>

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
                    <li class="active"><a href="/"><i class="icon-user icon white"></i> Administration</a></li>
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
                            <li><a href="/rest/item/items">All items</a></li>

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

<ul class="divider">
    <form action="/logout" method="post" accept-charset="UTF-8">
        <input class="btn btn-primary" style="width: 100px; height: 32px;
        font-size: 13px;" type="submit" name="logout" value="Logout" />
    </form>
</ul>

<div class="container-fluid" style="margin-top: 40px ">


                <div class="span7">
                    <h1 align="center">All items</h1>
                    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="itemtable">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                        <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                        </tr>
                        </tfoot>
                    </table>
</div>
    </div>
 <div class="modal fade hide"  id="myModal"  tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="false" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Edit item</h4>
            </div>
            <div class="modal-body" id="body" style="max-height: 600px">
                <form:form id="itemform"  method="post" action="/rest/item/add"  commandName="item" class="form-horizontal">

                <div class="control-group">
                    <form:hidden path="id" id="id"/>
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
            </div>
        </div>
     </div>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        $('#itemtable').dataTable( {
            "sAjaxSource": "/rest/item/" ,
            "sAjaxDataProp": "",
            "aoColumns": [
                { "mDataProp": "name" },

                {   "mData": "description"
                },

                { "mDataProp": "quantity" },

                {   "sDefaultContent": "",
                    "fnRender": function(o) { return '<form action="delete/' + o.aData["id"] + '" method="get"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>'}
                },
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
            "bProcessing": true
        } )

    } );

</script>
<script type="text/javascript">
    $(document).on("click", "[id^=editBtn]", function() {

        $.getJSON($(this).val(), function(item){

                    //$("[id^=modalRole]").removeAttr('checked');
                    $('#myModal').modal({
                        keyboard: false
                    })

                    $('#id').val(item.id);
                    $('#name').val(item.name);
                    $('#description').val(item.description);
                    $('#quantity').val(item.quantity);


                }
        );
        $('#myModal').on('hidden', function() {

            $(this).removeData('modal');

        });


    })
</script>
</html>