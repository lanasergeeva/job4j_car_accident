<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Редактирование</title>
</head>
<body>
<form action="<c:url value='/savemem?id=${accident.id}'/>" method='POST'>
    <div class="row" style="width: 650px; display:inline-block;">
        <span class="d-block p-2 bg-primary text-white text-align:center"><h3
                style="text-align:center">Создать</h3></span>
    </div>
    <br>
    <br>
    <div class="form-group row">
        <label for="inName" class="col-sm-2 col-form-label">Название</label>
        <div class="col-sm-30" style="width: 400px">
            <input type="text" class="form-control" name="name" value="${accident.name}" id="inName" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="inText" class="col-sm-2 col-form-label">Описание</label>
        <div class="col-sm-30" style="width: 400px;">
            <input type="text" class="form-control" id="inText" name="text" value="${accident.text}" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="inAd" class="col-sm-2 col-form-label">Адрес</label>
        <div class="col-sm-30" style="width: 400px;">
            <input type="text" class="form-control" id="inAd" name="address" value="${accident.address}" required>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Тип</label>
        <div class="col-sm-30" style="width: 400px;">
            <select class="custom-select input-sm" name="type.id">
                <c:forEach var="type" items="${types}">
                    <option value="${type.id}"
                            <c:if test="${type.id == accident.type.id}">
                                ${"selected"}
                            </c:if>
                    >${type.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Правило</label>
        <div cass="col-sm-30" style="width: 400px;">
            <select class="form-control" name="rIds" multiple>
                <c:forEach var="rule" items="${rules}">
                    <option value="${rule.id}"
                            <c:if test="${accident.rules.contains(rule)}">
                                ${"selected"}
                            </c:if>
                    >${rule.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Создать</button>
        </div>
    </div>
</form>
</body>
</html>
