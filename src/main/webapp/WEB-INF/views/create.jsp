<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Создание</title>
</head>
<body>
<form:form action="save" modelAttribute="accident">
    <form:hidden path="id"/>
    <div class="row" style="width: 650px; display:inline-block;">
        <span class="d-block p-2 bg-primary text-white text-align:center"><h3
                style="text-align:center">Создать</h3></span>
    </div>
    <br>
    <br>
    <div class="form-group row">
        <label for="inputEmail1" class="col-sm-2 col-form-label">Название</label>
        <div class="col-sm-30" style="width: 400px" ;>
            <form:input path="name" id="inputEmail1" placeholder="Название происшествия" style="width: 400px;"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="inputEmail4" class="col-sm-2 col-form-label">Описание</label>
        <div class="col-sm-30" style="width: 400px;">
            <form:textarea path="text" class="form-control" id="inputEmail4" placeholder="Описание" rows="3"
                           style="width: 400px;"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="inputEmail3" class="col-sm-2 col-form-label">Адрес</label>
        <div class="col-sm-30" style="width: 400px;">
            <form:input path="address" id="inputEmail3" placeholder="Адрес" style="width: 400px;"/>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Создать</button>
        </div>
    </div>
</form:form>
</body>
</html>
