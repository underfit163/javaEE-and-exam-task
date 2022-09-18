<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Альбомы</title>
    <link rel="stylesheet" href="StyleCSS/table.css">
    <link rel="shortcut icon" href="img/musicIcon.png" type="image/x-icon">
    <script src="jsFile/album.js" defer></script>
</head>
<body>
<ul class="nav">
    <li><a href="main" class="s2">Главная страница</a></li>
    <li><a href="musician" class="s2">Музыканты</a></li>
    <li><a href="album" class="s2">Альбомы</a></li>
    <li><a href="composition" class="s2">Композиции</a></li>
</ul>
<h1>Управление альбомами</h1>
<h2>Имеющиеся альбомы</h2>
<datalist id="nameDL"></datalist>
<datalist id="genreDL"></datalist>
<table id="tabMus">
    <caption>
        <button class="shine-button" onclick="openForm()">Добавить</button>
        <div class="form-popup" id="myForm">
            <form action="album" name="addForm" class="form-container" method="post"
                  onsubmit="addAjaxFunction(event); return false">
                <h1>Добавить альбом</h1>
                <label>
                    <b>Название альбома</b>
                    <input type="text" name="name" placeholder="Введите название" title="Введите название" list="nameDL" autocomplete="off" required>
                </label>
                <label>
                    <b>Жанр альбома</b>
                    <input type="text" name="genre" placeholder="Введите жанр" title="Введите жанр" list="genreDL" autocomplete="off" required>
                </label>
                <label>
                    <b>Музыкант</b> <br>
                    <select name="musician" title="Выберите музыканта" class="form-select">
                        <c:forEach var="musician" items="${musicians}">
                            <option value="${musician.idMusician}"
                                    title="${musician.idMusician}">${musician.nameMusician}</option>
                        </c:forEach>
                    </select>
                </label>
                <button type="submit" name="action" value="add" class="btn">Добавить</button>
                <button type="button" class="btn cancel" onclick="closeForm()">Закрыть</button>
            </form>
        </div>

        <button class="shine-button" onclick="openSelectForm()">Запросить</button>
        <div class="form-popup" id="selectForm">
            <form action="album" name="selectForm" class="form-container" method="post"
                  onsubmit="selectAjaxFunction(event); return false">
                <h3>Вывести все композиции альбома с длительностью большей указанной</h3>
                <button type="submit" name="action" value="select" class="btn">Запрос</button>
                <button type="button" class="btn cancel" onclick="closeSelectForm()">Закрыть</button>
                <label>
                    <b>Выберете альбом</b>
                    <select name="album" title="Выберите альбом" class="form-select">
                        <c:forEach var="album" items="${albums}">
                            <option value="${album.idAlbum}"
                                    title="${album.idAlbum}">${album.nameAlbum}</option>
                        </c:forEach>
                    </select>
                    <label>
                        <b>Длительность</b>
                        <input type="text" name="duration" placeholder="Введите минимальную длительность"
                               title="Введите минимальную длительность" pattern="[+]?\d+(\.\d+)?" value="0" required>
                    </label>
                </label>
            </form>
        </div>
    </caption>
    <tbody>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Название альбома</th>
        <th scope="col">Жанр альбома</th>
        <th scope="col">Музыкант</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <c:forEach var="album" items="${albums}">
        <tr id="tr${album.idAlbum}">
            <form id="${album.idAlbum}" name="udForm" action="album" method="POST"
                  onsubmit="udAjaxFunction(event); return false">
                <td><input type="text" name="id" size="3" value="${album.idAlbum}"
                           title="${album.idAlbum}" form="${album.idAlbum}" readonly></td>
                <td><input type="text" name="name" value="${album.nameAlbum}"
                           title="${album.nameAlbum}" form="${album.idAlbum}" list="nameDL" autocomplete="off"></td>
                <td><input type="text" name="genre" value="${album.genreAlbum}"
                           title="${album.genreAlbum}" form="${album.idAlbum}" list="genreDL" autocomplete="off"></td>
                <td>
                    <label>
                        <select name="musician" form="${album.idAlbum}">
                            <c:forEach var="musician" items="${musicians}">
                                <c:if test="${musician.idMusician == album.musician.idMusician}">
                                    <option value="${musician.idMusician}" title="${musician.idMusician}" selected>
                                            ${musician.nameMusician}</option>
                                </c:if>
                                <c:if test="${musician.idMusician != album.musician.idMusician}">
                                    <option value="${musician.idMusician}" title="${musician.idMusician}">
                                            ${musician.nameMusician}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </label>
                </td>
                <td>
                    <button id="uB${album.idAlbum}" type="submit" name="action" value="edit" class="shine-button"
                            form="${album.idAlbum}"><i>Обновить</i>
                    </button>
                </td>
                <td>
                    <button id="dB${album.idAlbum}" type="submit" name="action" value="delete" class="shine-button"
                            form="${album.idAlbum}"><i>Удалить</i></button>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
