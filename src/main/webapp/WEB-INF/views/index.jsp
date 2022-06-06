<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <title>Accident</title>
</head>
<body>

<c:url var="createButton" value="/create">
</c:url>
<c:url var="logoutRef" value="/logout">
</c:url>
<c:url var="indexRef" value="/">
</c:url>


<div class="container bg-dark text-white">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark rounded" aria-label="Twelfth navbar example">
        <div class="container-fluid">

            <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${indexRef}">Все нарушения</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${createButton}">Внести новое нарушение</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logoutRef}">${user.username} Выйти</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="p-3"><h1 style="text-align:center">Список нарушений</h1></div>
    <table class="table table-hover table-dark">
        <thead>
        <tr>
            <th>Название происшествия</th>
            <th>Описание</th>
            <th>Адрес</th>
            <th>Вид нарушения</th>
            <th>Статья</th>
            <th>Редактирование</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="acc" items="${all}">

            <c:url var="updateButton" value="/update">
                <c:param name="acId" value="${acc.id}"/>
            </c:url>
            <c:url var="deleteButton" value="/delete">
                <c:param name="acId" value="${acc.id}"/>
            </c:url>

            <tr>
                <td>${acc.name}</td>
                <td>${acc.text}</td>
                <td>${acc.address}</td>
                <td>${acc.type.name}</td>
                <td>
                    <c:forEach items="${acc.rules}" var="rule">
                        <c:out value="${rule.name}; "/>
                    </c:forEach>
                </td>
                <td><input type="button" value="Редактировать"
                           onclick="window.location.href ='${updateButton}'"/></td>
                <td><input type="button" value="Удалить"
                           onclick="window.location.href ='${deleteButton}'"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
