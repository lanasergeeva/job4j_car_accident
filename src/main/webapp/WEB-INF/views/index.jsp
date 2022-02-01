<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
    </tr>
    </thead>
    <tbody>
    <c:forEach var="acc" items="${all}">
        <tr>
            <td>${acc.name}</td>
            <td>${acc.text}</td>
            <td>${acc.address}</td>
        </tr>
    </c:forEach>
    </tbody>
</body>
</html>
