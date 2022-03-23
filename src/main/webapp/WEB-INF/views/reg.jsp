<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <title>Registration</title>
</head>
<body>
<c:url var="logButton" value="/login"></c:url>
<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            ${errorMessage}
    </div>
</c:if>
<form name='login' action="<c:url value='/reg'/>" method='POST'>
    <div class="form-group">
        <label for="exampleInputEmail1">Имя пользователя</label>
        <input type="text" name='username' style="width: 400px;" class="form-control" id="exampleInputEmail1"
               aria-describedby="emailHelp" placeholder="Введите Ваш логин">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Пароль</label>
        <input type="password" name='password' style="width: 400px;"
               class="form-control" id="exampleInputPassword1" placeholder="Введите пароль">
    </div>
    <button type="submit" name="submit" value="submit" class="btn btn-primary">Отправить</button>
    <input type="button" class="btn btn-primary" value="Уже есть аккаунт?"
           onclick="window.location.href ='${logButton}'"/>
</form>
</body>
</html>