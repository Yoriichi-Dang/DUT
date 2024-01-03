<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 12-Dec-23
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <div class="misc-wrapper">
        <h2 class="mb-2 mx-2">Chưa được truy cập</h2>
        <p class="mb-4 mx-2">${message}</p>
        <a href="<c:url value='/student-home-page?code=${USERMODEL.username}'/>" class="btn btn-primary">Back to home</a>
        <div class="mt-4">
            <img src="<c:url value='/template/admin/assets/img/illustrations/girl-doing-yoga-light.png'/>" alt="girl-doing-yoga-light" width="500" class="img-fluid" data-app-dark-img="illustrations/girl-doing-yoga-dark.png" data-app-light-img="illustrations/girl-doing-yoga-light.png">
        </div>
    </div>
</div>
</body>
</html>
