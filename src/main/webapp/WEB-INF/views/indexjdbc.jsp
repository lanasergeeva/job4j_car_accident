<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Accident</title>
</head>
<body>
<div>
    Login as : ${user.username}
</div>
<c:forEach items="${accidents}" var="accident">
    <c:out value="${accident.name}"/></br>
</c:forEach>
</body>
</html>
