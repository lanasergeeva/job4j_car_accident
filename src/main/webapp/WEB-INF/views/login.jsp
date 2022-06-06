<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <title>Login</title>
</head>
<body>

<c:url var="regButton" value="/reg"></c:url>

<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            ${errorMessage}
    </div>
</c:if>
<div id="login">
    <h3 class="text-center text-white pt-5">Пройдите процесс авторизации.</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form"
                          name='login' action="<c:url value='/login'/>" method='POST'>
                        <h3 class="text-center text-dark">Пройдите процесс авторизации.</h3>
                        <div class="form-group">
                            <label for="username" class="text-dark">Логин:</label><br>
                            <input type="text" autocomplete="off" name='username' id="username" class="form-control"
                                   placeholder="Введите логин">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-dark">Пароль:</label><br>
                            <input type="password" autocomplete="off" name='password' id="password" class="form-control"
                                   placeholder="Введите пароль">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-dark btn-md mr-5" value="Войти">
                            <input type="button" class="btn btn-dark btn-md" value="Зарегистрироваться"
                                   onclick="window.location.href ='${regButton}'"/>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>