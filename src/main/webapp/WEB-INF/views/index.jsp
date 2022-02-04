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
<span class="d-block p-4 bg-dark text-white"><h1 style="text-align:center">Дорожные происшествия</h1></span>
<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>Название происшествия</th>
        <th>Описание</th>
        <th>Адрес</th>
        <th>Тип</th>
        <th>Правило</th>
        <th>Редактирование</th>
        <th>Удалить</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="acc" items="${all}">

        <c:url var="createButton" value="/create">
        </c:url>
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
<div class="form-group row">
    <div class="col-sm-10">
        <input type="button" value="Создать новое"
               onclick="window.location.href='${createButton}'">
    </div>
</div>
</body>
</html>
