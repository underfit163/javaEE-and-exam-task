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
        if (x.type == 'text' && !x.readOnly && x.name == 'genre') {
            x.addEventListener("click", focusHandler2);
        }
    });
}
focusInput2();

var nameDataList = document.getElementById("nameDL");
var genreDataList = document.getElementById("genreDL");

function focusHandler1() {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            dataListHandler1(request);
        }
    }
    request.open("POST", "/album");
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
    request.open("POST", "/album");
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
    var genreDL = genreDataList.getElementsByTagName("option");
    var len = genreDL.length;
    if (genreDL.length != 0) {
        for (let i = len - 1; i >= 0; i--) {
            genreDL.item(i).remove();
        }
    }
    var parametr = request.responseText.split("&");
    var genres = [];
    for (i in parametr) {
        var j = parametr[i].split("=");
        genres.push(j[1]);
    }
    for (let i = 0; i < genres.length; i++) {
        var option1 = document.createElement("option");
        option1.value = genres[i];
        genreDataList.appendChild(option1);
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
    request.open("POST", "/album");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'action=option';
    request.send(body);
    document.getElementById("selectForm").style.display = "block";
}

function optionHandler(request) {
    var select1 = document.getElementById("selectForm").getElementsByTagName("select")[0];
    var options1 = document.getElementById("selectForm").getElementsByTagName("select")[0].options;
    var len = options1.length;
    if (options1.length != 0) {
        for (let i = len - 1; i >= 0; i--) {
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
    request.open("POST", "/album");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var selectForm = document.forms.namedItem("selectForm");
    var body = 'album=' + encodeURIComponent(selectForm.elements.namedItem("album").value) +
        '&duration=' + encodeURIComponent(selectForm.elements.namedItem("duration").value) +
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
    request.open("POST", "/album");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var addForm = document.forms.namedItem("addForm");
    var body = 'name=' + encodeURIComponent(addForm.elements.namedItem("name").value) +
        '&genre=' + encodeURIComponent(addForm.elements.namedItem("genre").value) +
        '&musician=' + encodeURIComponent(addForm.elements.namedItem("musician").value) +
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
    request.open("POST", "/album");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var body = 'id=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("input")[0].value) +
        '&name=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("input")[1].value) +
        '&genre=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("input")[2].value) +
        '&musician=' + encodeURIComponent(document.getElementById('tr' + id).getElementsByTagName("select")[0].value) +
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
    document.getElementById('tr' + data.get("id")).getElementsByTagName("input")[2].value = data.get("genre");
    document.getElementById('tr' + data.get("id")).getElementsByTagName("input")[2].title = data.get("genre");
    var options1 = document.getElementById('tr' + data.get("id")).getElementsByTagName("select")[0].options;
    for (let options1Element of options1) {
        if (options1Element.value == data.get("musician")) {
            options1Element.selected = true;
        }
    }
}

function deleteHandler(request) {
    document.getElementById('tr' + request.responseText.toString()).remove();
}