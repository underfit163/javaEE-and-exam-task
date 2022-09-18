<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isErrorPage="true"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Исключение</title>
    <link rel="shortcut icon" href="img/musicIcon.png" type="image/x-icon">
    <link rel="stylesheet" href="StyleCSS/table.css">
</head>
<body>
<ul class="nav">
    <li><a href="main.jsp" class="s2">Главная страница</a></li>
    <li><a href="musician.jsp" class="s2">Музыканты</a></li>
    <li><a href="album.jsp" class="s2">Альбомы</a></li>
    <li><a href="composition.jsp" class="s2">Композиции</a></li>
</ul>
<h1>Страница обработки ошибок</h1>
<h2>Исключение произошло при обработке запроса</h2>
<p>Type: <%=exception.toString()%>
</p>
<p>Message: <%=exception.getMessage()%>
</p>
</body>
</html>
