<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 04-Dec-23
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Can't access page</title>
</head>
<body>
<div class="container-xxl container-p-y">
    <div class="misc-wrapper">
        <h2 class="mb-2 mx-2">Can't access :(</h2>
        <p class="mb-4 mx-2">Oops! 😖 You requested URL can't access because account not allowed.</p>
        <a href="<c:url value="/login-page?action=login"/>" class="btn btn-primary">Back to home</a>
        <div class="mt-3">
            <img src="<c:url value="/template/admin/assets/img/illustrations/page-misc-error-light.png"/>" alt="page-misc-error-light" width="500" class="img-fluid" data-app-dark-img="illustrations/page-misc-error-dark.png" data-app-light-img="illustrations/page-misc-error-light.png">
        </div>
    </div>
</div>
</body>
</html>
