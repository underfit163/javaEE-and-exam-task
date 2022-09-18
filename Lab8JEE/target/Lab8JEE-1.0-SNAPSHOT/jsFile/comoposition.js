var focusInput1 = function () {
    document.querySelectorAll("input").forEach(x => {
        if (x.type == 'text' && !x.readOnly && x.name == 'name') {
            x.addEventListener("click", focusHandler1);
        }
    });
}
focusInput1();

var focusInput2 = function () {
    document.querySelectorAll("input").forEach(x => {
        if (x.type == 'text' && !x.readOnly && x.name == 'duration') {
            x.addEventListener("click", focusHandler2);
        }
    });
}
focusInput2();

var nameDataList = document.getElementById("nameDL");
var durationDataList = document.getElementById("durationDL");

function focusHandler1() {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            dataListHandler1(request);
        }
    }
    request.open("POST", "/composition");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'action=datalist1';
    request.send(body);
}

function focusHandler2() {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            dataListHandler2(request);
        }
    }
    request.open("POST", "/composition");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'action=datalist2';
    request.send(body);
}

function dataListHandler1(request) {
    var namesDL = nameDataList.getElementsByTagName("option");
    var len = namesDL.length;
    if (namesDL.length != 0) {
        for (let i = len - 1; i >= 0; i--) {
            namesDL.item(i).remove();
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

function dataListHandler2(request) {
    var durationDL = durationDataList.getElementsByTagName("option");
    var len = durationDL.length;
    if (durationDL.length != 0) {
        for (let i = len - 1; i >= 0; i--) {
            durationDL.item(i).remove();
        }
    }
    var parametr = request.responseText.split("&");
    var durations = [];
    for (i in parametr) {
        var j = parametr[i].split("=");
        durations.push(j[1]);
    }
    for (let i = 0; i < durations.length; i++) {
        var option1 = document.createElement("option");
        option1.value = durations[i];
        durationDataList.appendChild(option1);
    }
}

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

function addAjaxFunction(e) {
    e.preventDefault();
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            addHandler(request);
        }
    }
    request.open("POST", "/composition");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var addForm = document.forms.namedItem("addForm");
    var body = 'name=' + encodeURIComponent(addForm.elements.namedItem("name").value) +
        '&duration=' + encodeURIComponent(addForm.elements.namedItem("duration").value) +
        '&album=' + encodeURIComponent(addForm.elements.namedItem("album").value) +
        '&action=' + encodeURIComponent(addForm.elements.namedItem("action").value);
    request.send(body);
}

function addHandler(request) {
    var tabelMus = document.querySelector("#tabMus > tbody");
    tabelMus.insertAdjacentHTML("beforeend", request.responseText);
    focusInput1();
    focusInput2();
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
            else deleteHandler(request);
        }
    }
    request.open("POST", "/composition");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'id=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("input")[0].value) +
        '&name=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("input")[1].value) +
        '&duration=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("input")[2].value) +
        '&album=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("select")[0].value) +
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
    document.getElementById('tr' + data.get("id")).getElementsByTagName("input")[2].value = data.get("duration");
    document.getElementById('tr' + data.get("id")).getElementsByTagName("input")[2].title = data.get("duration");
    var options1 = document.getElementById('tr' + data.get("id")).getElementsByTagName("select")[0].options;
    for (let options1Element of options1) {
        if(options1Element.value == data.get("album")) {
            options1Element.selected = true;
        }
    }
}

function deleteHandler(request) {
    document.getElementById('tr' + request.responseText.toString()).remove();
}