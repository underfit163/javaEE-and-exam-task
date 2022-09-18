<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Музыканты</title>
    <link rel="stylesheet" href="StyleCSS/table.css">
    <link rel="shortcut icon" href="img/musicIcon.png" type="image/x-icon">
</head>
<body>
<ul class="nav">
    <li><a href="main.jsp" class="s2">Главная страница</a></li>
    <li><a href="musician.jsp" class="s2">Музыканты</a></li>
    <li><a href="album.jsp" class="s2">Альбомы</a></li>
    <li><a href="composition.jsp" class="s2">Композиции</a></li>
</ul>
<h1>Управление музыкантами</h1>
<h2>Имеющиеся музыканты</h2>
<table>
    <caption>Имеющиеся музыканты<br>
        ADD BUTTON!!!</caption>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Имя музыканта</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <c:forEach var="musician" items="${musicians}">
    <form action="musician" method="POST">
        <tr>
            <td><input type="text" name="id" size="3" value="${musician.id}"
                       title="${musician.id}" readonly></td>
            <td><input type="text" name="name" value="${musician.name}"
                       title="${musician.name}"></td>
            <td>
                <button type="submit" name="action" value="edit" class="shine-button"><i>Обновить</i>
                </button>
            </td>
            <td>
                <button type="submit" name="action" value="delete" class="shine-button"><i>Удалить</i></button>
            </td>
        </tr>
    </form>
    </c:forEach>
</table>
</body>
</html>
