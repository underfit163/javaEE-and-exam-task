var focusInput = function () {
    document.querySelectorAll("input").forEach(x => {
        if (x.type == 'text' && !x.readOnly) {
            x.addEventListener("click", focusHandler);
        }
    });
}
focusInput();

var nameDataList = document.getElementById("nameDL");

function focusHandler() {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            dataListHandler(request);
        }
    }
    request.open("POST", "/musician");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'action=datalist';
    request.send(body);
}

function dataListHandler(request) {
    var optionsDL = nameDataList.getElementsByTagName("option");
    var len = optionsDL.length;
    if(optionsDL.length != 0) {
        for (let i = len-1; i >= 0; i--) {
            optionsDL.item(i).remove();
        }
    }
    var parametr = request.responseText.split("&");
    var names = [];
    for (i in parametr) {
        var j = parametr[i].split("=");
        names.push(j[1]);
    }
    for (let i = 0; i < names.length; i++) {
        var option1 = document.createElement("option");
        option1.value = names[i];
        nameDataList.appendChild(option1);
    }
}

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

function openSelectForm() {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            optionHandler(request);
        }
    }
    request.open("POST", "/musician");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'action=option';
    request.send(body);
    document.getElementById("selectForm").style.display = "block";
}

function optionHandler(request) {
    var select1 = document.getElementById("selectForm").getElementsByTagName("select")[0];
    var options1 = document.getElementById("selectForm").getElementsByTagName("select")[0].options;
    var len = options1.length;
    if(options1.length != 0) {
        for (let i = len-1; i >= 0; i--) {
            options1.item(i).remove();
        }
    }

    var parametr = request.responseText.split("&");
    var ids = [];
    var names = [];
    var k = 0;
    for (i in parametr) {
        var j = parametr[i].split("=");
        if (k % 2 == 0) ids.push(j[1]);
        else if (k % 2 == 1) names.push(j[1]);
        k++;
    }
    for (let i = 0; i < ids.length; i++) {
        var option1 = document.createElement("option");
        option1.value = ids[i];
        option1.title = ids[i];
        option1.textContent = names[i];
        select1.append(option1);
    }
}

function closeSelectForm() {
    document.getElementById("selectForm").style.display = "none";
}

function selectAjaxFunction(e) {
    e.preventDefault();
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            selectHandler(request);
        }
    }
    request.open("POST", "/musician");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var selectForm = document.forms.namedItem("selectForm");
    var body = 'musician=' + encodeURIComponent(selectForm.elements.namedItem("musician").value) +
        '&action=' + encodeURIComponent(selectForm.elements.namedItem("action").value);
    request.send(body);
}

function selectHandler(request) {
    if (document.getElementById("selectTable")) document.getElementById("selectTable").remove();
    var tabelMus = document.getElementById("selectForm");
    tabelMus.insertAdjacentHTML("beforeend", request.responseText);
}

function addAjaxFunction(e) {
    e.preventDefault();
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            addHandler(request);
        }
    }
    var addForm = document.forms.namedItem("addForm");
    request.open("POST", "/musician");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'name=' + encodeURIComponent(addForm.elements.namedItem("name").value) +
        '&action=' + encodeURIComponent(addForm.elements.namedItem("action").value);
    request.send(body);
}

function addHandler(request) {
    var tabelMus = document.querySelector("#tabMus > tbody");
    tabelMus.insertAdjacentHTML("beforeend", request.responseText);
    focusInput();
    /*var parametr = request.responseText.split("&");
    var data = new Map();
    for (i in parametr) {
        var j = parametr[i].split("=");
        data.set(j[0], j[1]);
    }
    var tr = document.createElement("tr");
    tr.id = "tr" + data.get("id");
    var form1 = document.createElement("form");
    form1.id = "tr" + data.get("id");
    form1.name = "udForm";
    form1.action = "musician";
    form1.method = "POST";
    form1.addEventListener("submit",udAjaxFunction);

    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");

    var input1 = document.createElement("input");
    input1.type = "text";
    input1.name = "id";
    input1.size = "3";
    input1.value = data.get("id").toString();
    input1.title = data.get("id");
    input1.readOnly = true;

    var input2 = document.createElement("input");
    input2.type = "text";
    input2.name = "name";
    input2.value = data.get("name").toString();
    input2.title = data.get("name");

    var button1 = document.createElement("button");
    button1.type = "submit";
    button1.id = "uB"+data.get("id");
    button1.name = "action";
    button1.value = "edit";
    button1.className = "shine-button";

    var i1 = document.createElement("i");
    i1.innerText = "Обновить";
    button1.appendChild(i1);

    var button2 = document.createElement("button");
    button2.type = "submit";
    button2.id = "dB"+data.get("id");
    button2.name = "action";
    button2.value = "delete";
    button2.className = "shine-button";
    var i2 = document.createElement("i");
    i2.innerText = "Удалить";
    button2.appendChild(i2);

    td1.appendChild(input1);
    td2.appendChild(input2);
    td3.appendChild(button1);
    td4.appendChild(button2);

    tr.append(td1, td2, td3, td4);
    form1.append(tr);
    tabelMus.append(form1);*/
}

function udAjaxFunction(e) {
    e.preventDefault();
    var butVal = e.submitter.value;
    var id = e.submitter.id.toString().replace(/\D/g, '');
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            if (butVal == "edit")
                updateHandler(request);
            else if (butVal == "delete") deleteHandler(request);
        }
    }
    request.open("POST", "/musician");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'id=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("input")[0].value) +
        '&name=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("input")[1].value) +
        '&action=' + encodeURIComponent(butVal);
    request.send(body);
}

function updateHandler(request) {
    var parametr = request.responseText.split("&");
    var data = new Map();
    for (i in parametr) {
        var j = parametr[i].split("=");
        data.set(j[0], j[1]);
    }
    document.getElementById('tr' + data.get("id")).getElementsByTagName("input")[1].value = data.get("name");
    document.getElementById('tr' + data.get("id")).getElementsByTagName("input")[1].title = data.get("name");
}

function deleteHandler(request) {
    //document.getElementById(request.responseText.toString()).removeEventListener("submit", udAjaxFunction);
    document.getElementById('tr' + request.responseText.toString()).remove();
}