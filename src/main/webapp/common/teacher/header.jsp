<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 06-Dec-23
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<nav class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme" id="layout-navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value='/home-page'/>">
            <img style="width: 40px; height: 40px;" src="<c:url value='/template/admin/assets/img/favicon/favicon.ico'/>" alt="Logo" class="me-2">
        </a>
        <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <c:if test="${not empty USERMODEL and USERMODEL.roleCode eq 'TEACHER'}">

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="javascript:void(0)" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Cá nhân
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="<c:url value='/teacher-home-page?code=${USERMODEL.username}'/>">Thông tin cá nhân</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="<c:url value='/teacher-schelude-teach-page'/>">Lịch dạy trong ngày</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="javascript:void(0)" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Lớp
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="<c:url value='/teacher-class-course-teach-page'/>">Danh sách lớp dạy</a></li>
                    </ul>
                </li>

                    <%--                <li class="nav-item">--%>
                    <%--                    <a class="nav-link disabled" href="javascript:void(0)" tabindex="-1">Disabled</a>--%>
                    <%--                </li>--%>
            </ul>
            <form class="d-flex mb-0" onsubmit="return false">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-primary" type="submit">Search</button>
            </form>
        </c:if>
    </div>


    <div class=" d-flex align-items-center " id="navbar-collapse">
        <ul class="navbar-nav flex-row align-items-center ms-auto">
            <li class="nav-item navbar-dropdown dropdown-user dropdown">
                <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                    <c:if test="${empty USERMODEL}">
                        <div class="avatar avatar-busy">
                            <img src="<c:url value='/template/admin/assets/img/avatars/avatar.jpg'/>" alt="" class="w-px-40 h-auto rounded-circle">
                        </div>
                    </c:if>
                    <c:if test="${not empty USERMODEL}">
                        <div class="avatar avatar-online">
                            <img src="<c:url value='${model.image!=null?model.image:"/template/admin/assets/img/avatars/avatar.jpg"}'/>" alt="" class="w-px-40 h-auto rounded-circle">
                        </div>
                    </c:if>

                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                    <c:if test="${not empty USERMODEL}">
                    <li>
                        <a class="dropdown-item" href="#">
                            <div class="d-flex">

                                    <div class="flex-shrink-0 me-3">
                                        <div class="avatar avatar-online">
                                            <img src="<c:url value='${model.image!=null?model.image:"/template/admin/assets/img/avatars/avatar.jpg"}'/>" alt="" class="w-px-40 h-auto rounded-circle">
                                        </div>
                                    </div>
                                    <div class="flex-grow-1">
                                        <span class="fw-semibold d-block">${USERMODEL.username}</span>
                                        <small class="text-muted">${USERMODEL.roleCode}</small>
                                    </div>

                            </div>
                        </a>
                    </li>
                    </c:if>
                    <c:if test="${not empty USERMODEL}">
                        <li>
                            <div class="dropdown-divider"></div>
                        </li>
<%--                        <li>--%>
<%--                            <a class="dropdown-item" href="<c:url value='/student-home-page'/>">--%>
<%--                                <i class="bx bx-user me-2"></i>--%>
<%--                                <span class="align-middle">My Account</span>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a class="dropdown-item" href="#">--%>
<%--                                <i class="bx bx-cog me-2"></i>--%>
<%--                                <span class="align-middle">Settings</span>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <div class="dropdown-divider"></div>--%>
<%--                        </li>--%>
                    </c:if>
                    <li>
                        <c:if test="${empty USERMODEL}">
                            <a class="dropdown-item" href="<c:url value='/login-page?action=login'/>">
                                <i class="bx bx-power-off me-2"></i>
                                <span class="align-middle">Login</span>
                            </a>
                        </c:if>
                        <c:if test="${not empty USERMODEL}">
                            <a class="dropdown-item" href="<c:url value='/login-page?action=logout'/>">
                                <i class="bx bx-power-off me-2"></i>
                                <span class="align-middle">Logout</span>
                            </a>
                        </c:if>
                    </li>
                </ul>
            </li>
            <!--/ User -->
        </ul>
    </div>
</nav>