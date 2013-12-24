<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="select" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/cupertino/jquery-ui.css" />

    <script src="/static/js/bootstrap.js"></script>
    <script src="/static/js/bootstrap-confirmation.js"></script>
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.js"></script>
    <script type="text/javascript" src="../../static/js/DT_bootstrap.js"></script>
    <script src="/static/js/tooltip.js"></script>

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
        .span12
        {
            background-color:#eee;
            border: 1px solid #888;
            border-radius:3px;
        }
    </style>




</head>

<body>

<!--Блок для отображения имени пользователя-->
<%--<div class="username">
    <h4><%= request.getRemoteUser() %></h4>
</div>--%>


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
                    <li class="dropdown active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-shopping-cart icon white"></i> Orders <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/order">New order</a></li>
                            <li class="divider"></li>
                            <li><a href="/orders">My orders</a></li>

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

   <div class="row" style="margin-top: 40px">
    <div class="span 12">
        <button class="btn btn-primary" id="add_item" name = "add_item" data-toggle="tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Click to add item">Add item</button>
        <h1 align="center">Order details</h1>
        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="orderDetailsTable">
            <thead>
            <tr>
                <th>Item #</th>
                <th>Item name</th>
                <th>Item description</th>
                <th>Dimension</th>
                <th>Price</th>
                <th>Price per line</th>
                <th>Quantity</th>
                <th>&nbsp;</th>

            </tr>
            </thead>
            <tbody>

            </tbody>
            <tfoot>
            <tr>
                <th>Item #</th>
                <th>Item name</th>
                <th>Item description</th>
                <th>Dimension</th>
                <th>Price</th>
                <th>Price per line</th>
                <th>Quantity</th>
                <th>&nbsp;</th>

            </tr>
            </tfoot>
        </table>


    </div>
   </div>
     <div class="row">
        <div class="span12">
            <div class="row">
        <form:form id="orderform"  method="post" action="/rest/order/add"  commandName="order" class="form-horizontal">
        <div class="span5" style="margin-top: 40px">

             <form:hidden path="customerId" value= "${user.id}" />
            <div class="control-group">
                <form:label cssClass="control-label" path="id"  data-toggle="tooltip" data-placement="top" title="" data-original-title="Number of current order">Order #:</form:label>
                <div  class="controls">
                    <form:input id="id" path="id"/>

                </div>
            </div>
                <div class="control-group">
                    <form:label cssClass="control-label" path="status" data-toggle="tooltip" data-placement="top" title="" data-original-title="Status of current order">Status:</form:label>
                    <div class="controls">
                        <form:select path="status">
                            <form:option value="Created"></form:option>
                            <form:option value="Pending"></form:option>
                            <form:option value="Ordered"></form:option>
                            <form:option value="Delivered"></form:option>
                        </form:select>
                    </div>
                </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="totalPrice" data-toggle="tooltip" data-placement="top" title="" data-original-title="Price of all ordered items">Total price:</form:label>
                <div class="controls">
                    <form:input id="totalPrice" path="totalPrice"/>
                </div>
            </div>
                <div class="control-group">
                    <label for="totalItems" class="control-label" >Total items:</label>
                    <div class="controls">
                        <input type="text" id="totalItems" />
                    </div>
                </div>
            <div class="control-group">
                 <form:label cssClass="control-label" path="creationDate"  data-toggle="tooltip" data-placement="top" title="" data-original-title="Date of creation order" >Date of ordering:</form:label>
                <div class="controls">
                    <fmt:formatDate value="${order.creationDate}" var="creationDate" pattern="dd/MM/yyyy" />
                    <form:input id="creationDate" path="creationDate" value="${creationDate}" disabled="true" />
                   <%-- <form:input id="creationDate" path="creationDate"/>--%>
                </div>
            </div>
                <div class="control-group">
                    <form:label cssClass="control-label" path="preferableDate"  data-toggle="tooltip" data-placement="top" title="" data-original-title="Choose preferable date of delivering">Preferable delivery date:</form:label>
                    <div class="controls">
                        <fmt:formatDate value="${order.preferableDate}" var="preferableDate" pattern="dd/MM/yyyy" />
                        <form:input id="preferableDate" path="preferableDate" value="${preferableDate}"  />
                    </div>
                </div>
                <div class="control-group">
                    <form:label cssClass="control-label" path="deliveryDate"  data-toggle="tooltip" data-placement="top" title="" data-original-title="Date of delivering">Delivery date:</form:label>
                    <div class="controls">
                        <fmt:formatDate value="${order.deliveryDate}" var="deliveryDate" pattern="dd/MM/yyyy" />
                        <form:input id="deliveryDate" path="deliveryDate" value="${deliveryDate}" disabled="true" />
                    </div>
                </div>

        <div class="control-group">
            <form:label cssClass="control-label" path="merchId"  data-toggle="tooltip" data-placement="top" title="" data-original-title="Please select merchandiser">Assignee:</form:label>
            <div class="controls">
                <form:select path="merchId">
                    <c:forEach items="${merchs}" var="merch">
                    <form:option value="${merch.id}">${merch.login}</form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        </div>


        <div class=" span5 offset1" style="margin-top: 40px">
            <div class="control-group">
                <input type="hidden" id="orderId" name="orderId" value="${order.id}">
                <form:label cssClass="control-label" path="payment.type"  data-toggle="tooltip" data-placement="top" title="" data-original-title="Choose type of credit card or skip this and enter credit card number. Type will be chosen automaticly">Credit card type:</form:label>
                <div class="controls">
                    <form:select id="cctype" path="payment.type">
                        <form:option value="Visa"></form:option>
                        <form:option value="MasterCard"></form:option>
                        <form:option value="AmericanExpress"></form:option>
                        <form:option value="Maestro"></form:option>
                    </form:select>
                    <img id="cc"  width="58px" height="36px"/>
                </div>

            </div>

            <div class="control-group">
                <form:label cssClass="control-label" path="payment.creditCardNumber" data-toggle="tooltip" data-placement="top" title="" data-original-title="Enter your credit card number" >Credit card number:</form:label>
                <div class="controls">
                    <form:input id="payment.creditCardNumber" path="payment.creditCardNumber"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="payment.CVV2Code" data-toggle="tooltip" data-placement="top" title="" data-original-title="Enter CVV2 code">CVV2 code:</form:label>
                <div class="controls">
                    <form:input id="payment.CVV2Code" path="payment.CVV2Code"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="payment.expiryDate" data-toggle="tooltip" data-placement="top" title="" data-original-title="Select credit card expiry date">Expiry date:</form:label>
                <div class="controls">
                    <fmt:formatDate value="${order.payment.expiryDate}" var="expiryDate" pattern="dd/MM/yyyy" />
                    <form:input id="expiryDate" path="payment.expiryDate" value="${expiryDate}" />
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="payment.startDate" data-toggle="tooltip" data-placement="top" title="" data-original-title="This option is aviable only for Maestro cards">Start date (Maestro only):</form:label>
                <div class="controls">
                    <fmt:formatDate value="${order.payment.startDate}" var="startDate" pattern="dd/MM/yyyy" />
                    <form:input id="startDate" path="payment.startDate" value="${startDate}" disabled="true" />
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="payment.issueNumber" data-toggle="tooltip" data-placement="top" title="" data-original-title="This option is aviable only for Maestro cards">Issue number (Maestro only):</form:label>
                <div class="controls">
                    <form:input id="payment.issueNumber" path="payment.issueNumber" disabled="true"/>
                </div>
            </div>
            </div>
          </div>
    <div class="row" style="border-top: InactiveBorder; border-bottom: inactiveborder">
        <div class="pagination-centered">
            <div class="control-group">
                <div class="controls">
                    <input type="submit"  value="Add Order" class="btn btn-success" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to create or update your order"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    </div>
     </div>
        </div>
    </div>
</div>
<div class="modal fade hide"  id="myModal" style="width:600px;"  tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="false" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Add new item</h4>
            </div>
            <div class="modal-body" id="body" style="max-height: 600px">
                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="itemTable">
                    <thead>
                    <tr>
                        <th>Item name</th>
                        <th>Item description</th>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                    <tr>
                        <th>Item name</th>
                        <th>Item description</th>
                        <th>&nbsp;</th>
                    </tr>
                    </tfoot>
                </table>
                <form:form class="form-horizontal" commandName="orderdetail" action="/rest/order/details/add">

                     <input type="hidden" id="oid" name="oid" value="${order.id}"/>
                 <div class="control-group">
                     <input type="hidden" name="pid"  id="priceId"/>
                        <form:label cssClass="control-label" path="price.itemId" data-toggle="tooltip" data-placement="top" title="" data-original-title="You add this item to cart">Item:</form:label>
                    <div class="controls">
                        <form:input id="item" type="text" path="price.itemId" disabled="true"/>
                    </div>
                     </div>

                     <div class="control-group">
                         <form:label path="price.price" cssClass="control-label" data-toggle="tooltip" data-placement="top" title="" data-original-title="Price for each additional dimension">Price:</form:label>
                         <div class="controls">
                             <form:input id="price" path="price.price" type="text" disabled="true"/>
                         </div>
                     </div>
                     <div class="control-group">
                         <form:label path="quantity" cssClass="control-label" data-toggle="tooltip" data-placement="top" title="" data-original-title="Type quantity of dimension">Quantity:</form:label>
                         <div class="controls">
                             <form:input path="quantity" id="quantity" type="text"/>
                         </div>
                     </div>
               <%-- <div class="control-group">
                    <label for="quantity" class="control-label">Quantity:</label>
                    <div class="controls">
                        <input type="text" name="quantity" id="quantity"/>
                    </div>
                </div>--%>
                     <div class="control-group">
                         <form:label path="price.dimensionId" cssClass="control-label" data-toggle="tooltip" data-placement="top" title="" data-original-title="Select dimansion">Dimension:</form:label>
                         <div class="controls">
                             <form:select id="dimensionId" name="dm" path="price.dimensionId" >

                                 <c:forEach items="${dimensions}" var="dimension">
                                     <form:option value="${dimension.dimensionId}"> ${dimension.name} </form:option>
                                 </c:forEach>
                             </form:select>
                         </div>
                     </div>



                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <input type="submit" id="modalSubmit" value="Add to order" class="btn btn-primary"/>

                </div>
                </form:form>

            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).on("click", "[id^=addBtn]", function() {

        $.getJSON("rest/item/price/"+$(this).val(), function setPrice(priceArr){
             window.jsonArray = priceArr;
            for (var i =0; i <priceArr.length;i++){
             if (priceArr[i].dimensionId.dimensionId == $('#dimensionId').val()){
                  $('#item').val(priceArr[i].itemId.name);
                 $('#price').val(priceArr[i].price);
                 $('#priceId').val(priceArr[i].id);
             }
            }
    });
    })
</script>
<script type="text/javascript">
    $('#dimensionId').change(function(){
        for (var i =0; i <jsonArray.length;i++){
            if (jsonArray[i].dimensionId.dimensionId == $('#dimensionId').val()){
                $('#item').val(jsonArray[i].itemId.name);
                $('#price').val(jsonArray[i].price);
                $('#priceId').val(jsonArray[i].id);
            }
        }
    });
</script>
<script type="text/javascript">

    $(function() {
        $( '#creationDate, #preferableDate, #deliveryDate, #startDate, #expiryDate'  ).datepicker({

            firstDay: 1,
            dateFormat: 'dd/mm/yy'
        });
    });
</script>
<script type="text/javascript">
    $(document).on("click", "#add_item", function() {




                    $('#myModal').modal({
                        keyboard: false
                    })



                }
        );
        $('#myModal').on('hidden', function() {

            $(this).removeData('modal');

        });



</script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#itemTable').dataTable( {
            "sAjaxSource": "/rest/item/" ,
            "sAjaxDataProp": "",
            "aoColumns": [
                { "mDataProp": "name" },

                {   "mData": "description"
                },
                {   "sDefaultContent": "",
                    "fnRender": function(o) { return '<button class="btn-mini btn-success" id="addBtn' + o.aData["id"] + '" value="'+o.aData["id"]+'">Add</button>'}
                }

            ],

            "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_ records per page"
            },
            "iDisplayLength": 5,
            "aLengthMenu": [[5], [5]],
            "bProcessing": true
        } ).columnFilter({
                    aoColumns: [
                        {type: "text"},
                        {type: "text"},
                        null]});

    });


</script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#orderDetailsTable').dataTable( {
            "fnDrawCallback": function ( oSettings ) {
                /* Need to redo the counters if filtered or sorted */
                if ( oSettings.bSorted || oSettings.bFiltered )
                {
                    for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )
                    {
                        $('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+1 );
                    }
                }
            },
            "aoColumnDefs": [
                { "bSortable": false, "aTargets": [ 0 ] }
            ],
            "aaSorting": [[ 1, 'asc' ]],
            "sAjaxSource": "/rest/order/details/all/"+${order.id} ,
            "sAjaxDataProp": "",
            "aoColumns": [
                { "mDataProp": "price.itemId.id" },
                { "mDataProp": "price.itemId.name" },
                { "mDataProp": "price.itemId.description" },
                { "mDataProp": "price.dimensionId.name" },
                { "mDataProp": "price.price" },
                {
                    "mData":"quantity",
                    "mRender": function ( data, type, row ) {
            return data*row.price.price
        }},
                { "mDataProp": "quantity" },

                {   "sDefaultContent": "",
                    "fnRender": function(o) { return '<input type="button" id="delete/'+o.aData["id"]+ '" class="btn btn-danger btn-mini"  value="Delete" data-toggle="confirmation" data-href="/rest/order/details/delete/'+${order.id}+'/'+o.aData["id"]+'" />'}
                    //{ return '<button class="btn-mini btn-danger" id="deleteBtn' + o.aData["id"] + '" value="'+o.aData["id"]+'">Delete</button>'}
                }

            ],



            "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_ records per page"
            },
            "iDisplayLength": 5,
            "aLengthMenu": [[5], [5]],
            "bProcessing": true,
            "fnDrawCallback": function() {
                $("[id^=delete]").confirmation({singleton: true, popout:true});
            }
           })

    });


</script>
<script type="text/javascript">
    function getCreditCardType(accountNumber)
    {

        //start without knowing the credit card type
        var result = "unknown";

        //first check for MasterCard
        if (/^5[1-5]/.test(accountNumber))
        {
            result = "mastercard";
        }

        //then check for Visa
        else if (/^4/.test(accountNumber))
        {
            result = "visa";
        }

        //then check for AmEx
        else if (/^3[47]/.test(accountNumber))
        {
            result = "amex";
        }

        return result;
    }
</script>
<script type="text/javascript">
    function handleEvent(event)
    {
        var value   = event.target.value,
                type    = getCreditCardType(value);

        switch (type)
        {
            case "mastercard":

                $('#cc').attr('src',"/../../static/img/mastercard.jpg");
                $('#cctype').val("MasterCard");

                break;

            case "visa":
                $('#cc').attr('src',"/../../static/img/visa.jpg");
                $('#cctype').val("Visa");
                //show Visa icon
                break;

            case "amex":
                $('#cc').attr('src',"/../../static/img/americanexpress.jpg");
                $('#cctype').val("AmericanExpress");
                //show American Express icon
                break;

            default:
            //clear all icons?
            //show error?
        }
    }

    // or window.onload
    document.addEventListener("DOMContentLoaded", function(){
        var textbox = document.getElementById("payment.creditCardNumber");
        textbox.addEventListener("keyup", handleEvent, false);
        textbox.addEventListener("blur", handleEvent, false);
    }, false);
</script>
<script type="text/javascript">
    $('#cctype').on('change', function(){

        switch (this.value)
        {
            case "MasterCard":

                $('#cc').attr('src',"/../../static/img/mastercard.jpg");


                break;

            case "Visa":
                $('#cc').attr('src',"/../../static/img/visa.jpg");

                //show Visa icon
                break;

            case "AmericanExpress":
                $('#cc').attr('src',"/../../static/img/americanexpress.jpg");

                //show American Express icon
                break;
            case "Maestro":
                $('#cc').attr('src',"/../../static/img/maestro.jpg");
            default:
            //clear all icons?
            //show error?
        }
    })
</script>

</html>