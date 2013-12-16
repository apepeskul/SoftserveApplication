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
