<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Создание</title>
</head>
<body>

<c:url var="createButton" value="/create">
</c:url>
<c:url var="logoutRef" value="/logout">
</c:url>
<c:url var="indexRef" value="/">
</c:url>


<div class="container bg-dark text-white">
    <nav class="navbar navbar-expand-lg navbar-dark rounded" aria-label="Twelfth navbar example">
        <div class="container-fluid bg-dark text-white">
            <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link " aria-current="page" href="${indexRef}">Все нарушения</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="${createButton}">Внести новое нарушение</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logoutRef}">${user.username} Выйти</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="row justify-content-md-center">
        <form:form action="save" modelAttribute="accident">
            <form:hidden path="id"/>
            <br>
            <br>
            <div class="form-group row">
                <label for="inputEmail1" class="col-sm-2 col-form-label mr-5">Название</label>
                <div class="col-sm-30" style="width: 400px">
                    <form:input path="name" id="inputEmail1" autocomplete="off" placeholder="Название происшествия"
                                style="width: 400px;"/>
                </div>
            </div>

            <div class="form-group row">
                <label for="inputEmail4" class="col-sm-2 col-form-label mr-5">Описание</label>
                <div class="col-sm-30" style="width: 400px;">
                    <form:textarea path="text" class="form-control" id="inputEmail4" autocomplete="off"
                                   placeholder="Описание"
                                   rows="3"
                                   style="width: 400px;"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label mr-5">Адрес</label>
                <div class="col-sm-30" style="width: 400px;">
                    <form:input path="address" id="inputEmail3" autocomplete="off" placeholder="Адрес"
                                style="width: 400px;"/>
                </div>
            </div>

            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label mr-5">Вид нарушения</label>
                <div class="col-sm-30" style="width: 400px;">
                    <form:select path="type.id" class="custom-select" id="inputGroupSelect01">
                        <form:options items="${types}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label mr-5">Статья</label>
                <div class="col-sm-30" style="width: 400px;">
                    <select class="custom-select" name="rIds" multiple>
                        <c:forEach var="rule" items="${rules}">
                            <option value="${rule.id}">${rule.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <input type="submit" name="submit" class="btn btn-lg btn-light btn-md"
                       value="Создать">
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
