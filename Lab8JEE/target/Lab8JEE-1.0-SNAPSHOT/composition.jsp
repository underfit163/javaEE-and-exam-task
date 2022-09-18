<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Композиции</title>
    <link rel="stylesheet" href="StyleCSS/table.css">
    <link rel="shortcut icon" href="img/musicIcon.png" type="image/x-icon">
    <script src="jsFile/comoposition.js" defer></script>
</head>
<body>
<ul class="nav">
    <li><a href="main" class="s2">Главная страница</a></li>
    <li><a href="musician" class="s2">Музыканты</a></li>
    <li><a href="album" class="s2">Альбомы</a></li>
    <li><a href="composition" class="s2">Композиции</a></li>
</ul>
<h1>Управление композициями</h1>
<h2>Имеющиеся композиции</h2>
<datalist id="nameDL"></datalist>
<datalist id="durationDL"></datalist>
<table id="tabMus">
    <caption>
        <button class="shine-button" onclick="openForm()">Добавить</button>
        <div class="form-popup" id="myForm">
            <form action="composition" name="addForm" class="form-container" method="post"
                  onsubmit="addAjaxFunction(event); return false">
                <h1>Добавить композицию</h1>
                <label>
                    <b>Название композиции</b>
                    <input type="text" name="name" placeholder="Введите название" title="Введите название" list="nameDL" autocomplete="off" required>
                </label>
                <label>
                    <b>Длительность</b>
                    <input type="text" name="duration" placeholder="Введите длительность" title="Введите длительность"
                           pattern="[+]?\d+(\.\d+)?" list="durationDL" autocomplete="off" required>
                </label>
                <label>
                    <b>Альбом</b><br>
                    <select name="album" title="Выберите альбом" class="form-select">
                        <c:forEach var="album" items="${albums}">
                            <option value="${album.idAlbum}" title="${album.idAlbum}">${album.nameAlbum}</option>
                        </c:forEach>
                    </select>
                </label>
                <button type="submit" name="action" value="add" class="btn">Добавить</button>
                <button type="button" class="btn cancel" onclick="closeForm()">Закрыть</button>
            </form>
        </div>
    </caption>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Название композиции</th>
        <th scope="col">Длительность</th>
        <th scope="col">Альбом</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <c:forEach var="composition" items="${compositions}">
        <tr id="tr${composition.idComposition}">
            <form id="${composition.idComposition}" name="udForm" action="composition" method="POST"
                  onsubmit="udAjaxFunction(event); return false">
                <td><input type="text" name="id" size="3" value="${composition.idComposition}"
                           title="${composition.idComposition}" form="${composition.idComposition}" readonly></td>
                <td><input type="text" name="name" value="${composition.nameComposition}"
                           title="${composition.nameComposition}" form="${composition.idComposition}" list="nameDL" autocomplete="off"></td>
                <td><input type="text" name="duration" value="${composition.durationComposition}"
                           title="${composition.durationComposition}" size="10" pattern="[+]?\d+(\.\d+)?"
                           form="${composition.idComposition}" list="durationDL" autocomplete="off"></td>
                <td>
                    <label>
                        <select name="album" form="${composition.idComposition}">
                            <c:forEach var="album" items="${albums}">
                                <c:if test="${album.idAlbum == composition.album.idAlbum}">
                                    <option value="${album.idAlbum}" title="${album.idAlbum}" selected>
                                            ${album.nameAlbum}</option>
                                </c:if>
                                <c:if test="${album.idAlbum != composition.album.idAlbum}">
                                    <option value="${album.idAlbum}" title="${album.idAlbum}">
                                            ${album.nameAlbum}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </label>

                <td>
                    <button id="uB${composition.idComposition}" type="submit" name="action" value="edit" class="shine-button" form="${composition.idComposition}"><i>Обновить</i>
                    </button>
                </td>
                <td>
                    <button id="uB${composition.idComposition}" type="submit" name="action" value="delete" class="shine-button" form="${composition.idComposition}"><i>Удалить</i></button>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
