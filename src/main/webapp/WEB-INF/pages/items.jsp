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
    <link rel="stylesheet" type="text/css" href="/static/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap-responsive.css" />

    <script src="/static/js/bootstrap.js"></script>
    <script src="/static/js/bootstrap-confirmation.js"></script>
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.js"></script>
    <script type="text/javascript" src="/static/js/DT_bootstrap.js"></script>


    <script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
    <script src="http://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery.dataTables.columnFilter.js"></script>

    <!-- Стиль для отображения имени пользователя -->


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

<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Order Management System</a>
            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="#"><i class="icon-home icon-white"></i> Home</a></li>
                    <li><a href="/admin"><i class="icon-user icon white"></i> Administration</a></li>
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
    <div class="span 12 " style="margin-top: 40px">
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
                    "fnRender": function(o)  { return '<input type="button" id="delete/'+o.aData["id"]+ '" class="btn btn-danger btn-mini"  value="Delete" data-toggle="confirmation" data-href="/rest/item/delete/'+o.aData["id"]+'" />'}

                },
                {   "sDefaultContent": "",
                    "fnRender": function(o) { return '<button class="btn-mini btn-warning" id="editBtn' + o.aData["id"] + '" value="'+o.aData["id"]+'" data-toggle="modal">Edit</button>'}
                }
            ],
            "bFilter":false,

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

    } );

</script>
<script type="text/javascript">
    $(document).on("click", "[id^=editBtn]", function() {

        $.getJSON("/rest/item/"+$(this).val(), function(item){

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