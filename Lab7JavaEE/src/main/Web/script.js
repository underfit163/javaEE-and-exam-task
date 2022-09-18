const but = document.inputData.calcName;
but.addEventListener("click", calcHandler);

function calcHandler(e) {
    if ((document.getElementById('firstOp').value == "") || (document.getElementById('secondOp').value == "")) {
        alert('Надо заполнить все поля операндов!');
        e.preventDefault();
    } else {
        let op1 = document.getElementById('firstOp').value;
        let op2 = document.getElementById('secondOp').value;
        let operation = document.getElementById('div');
        if (parseFloat(op1) == op1 && parseFloat(op2) == op2) {
            if (operation.checked && op2 == 0) {
                alert('Делить на ноль нельзя!');
                e.preventDefault();
            }
        } else {
            alert("Вводите только числа!");
            e.preventDefault();
        }
    }
}

function getСalc() {
    let p_url = location.search.substring(1);
    let parametr = p_url.split("&");
    let values = [];
    let t = 0;
    for (i in parametr) {
        var j = parametr[i].split("=");
        values[t++] = j[1];
    }
    let op1 = values[0];
    let op2 = values[1];
    let operation = decodeURIComponent(values[2]);
    let result;
    if (operation == "+") {
        result = parseFloat(op1) + parseFloat(op2);
    } else if (operation == "-") {
        result = op1 - op2;
    } else if (operation == "*") {
        result = op1 * op2;
    } else if (operation == "/") {
        result = op1 / op2;
    } else {
        alert('Неизвестная ошибка!');
        e.preventDefault();
    }
    document.getElementById("firstOp").innerHTML = op1;
    document.getElementById("secondOp").innerHTML = op2;
    document.getElementById('operation').innerHTML = operation;
    document.getElementById("result").innerHTML = result;
}


