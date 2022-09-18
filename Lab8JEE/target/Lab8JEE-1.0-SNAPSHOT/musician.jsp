<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Музыканты</title>
    <link rel="stylesheet" href="StyleCSS/table.css">
    <link rel="shortcut icon" href="img/musicIcon.png" type="image/x-icon">
    <script src="jsFile/musician.js" defer></script>
</head>
<body>
<ul class="nav">
    <li><a href="main" class="s2">Главная страница</a></li>
    <li><a href="musician" class="s2">Музыканты</a></li>
    <li><a href="album" class="s2">Альбомы</a></li>
    <li><a href="composition" class="s2">Композиции</a></li>
</ul>
<h1>Управление музыкантами</h1>
<h2>Имеющиеся музыканты</h2>
<datalist id="nameDL"></datalist>
<table id="tabMus">
    <caption>
        <button class="shine-button" onclick="openForm()">Добавить</button>
        <div class="form-popup" id="myForm">
            <form action="musician" name="addForm" class="form-container" method="post"
                  onsubmit="addAjaxFunction(event); return false">
                <h1>Добавить музыканта</h1>
                <label>
                    <b>Имя музыканта</b>
                    <input type="text" name="name" placeholder="Введите имя" title="Введите имя" list="nameDL" autocomplete="off" required>
                </label>
                <button type="submit" id="addMusButton" name="action" value="add" class="btn">Добавить</button>
                <button type="button" class="btn cancel" onclick="closeForm()">Закрыть</button>
            </form>
        </div>

        <button class="shine-button" onclick="openSelectForm()">Запросить</button>
        <div class="form-popup" id="selectForm">
            <form action="album" name="selectForm" class="form-container" method="post"
                  onsubmit="selectAjaxFunction(event); return false">
                <h3>Вывести все композиции музыканта</h3>
                <button type="submit" name="action" value="select" class="btn">Запрос</button>
                <button type="button" class="btn cancel" onclick="closeSelectForm()">Закрыть</button>
                <label>
                    <b>Выберете музыканта</b>
                    <select name="musician" title="Выберите музыканта" class="form-select">
                        <c:forEach var="musician" items="${musicians}">
                            <option value="${musician.idMusician}"
                                    title="${musician.idMusician}">${musician.nameMusician}</option>
                        </c:forEach>
                    </select>
                </label>
            </form>
        </div>
    </caption>
    <tbody>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Имя музыканта</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <c:forEach var="musician" items="${musicians}">
        <tr id="tr${musician.idMusician}">
            <form id="${musician.idMusician}" name="udForm" action="musician" method="POST"
                  onsubmit="udAjaxFunction(event); return false">
                <td><input type="text" name="id" size="3" value="${musician.idMusician}"
                           title="${musician.idMusician}" form="${musician.idMusician}" readonly></td>
                <td><input type="text" name="name" value="${musician.nameMusician}"
                           title="${musician.nameMusician}" form="${musician.idMusician}" list="nameDL" autocomplete="off"></td>
                <td>
                    <button type="submit" id="uB${musician.idMusician}" name="action" value="edit" class="shine-button"
                            form="${musician.idMusician}"><i>Обновить</i>
                    </button>
                </td>
                <td>
                    <button type="submit" id="dB${musician.idMusician}" name="action" value="delete"
                            class="shine-button" form="${musician.idMusician}">
                        <i>Удалить</i></button>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
