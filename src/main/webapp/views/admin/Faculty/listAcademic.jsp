<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 02-Dec-23
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-academic"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">${model.name} /</span> Ngành</h4>
    <div class="card">
        <h5 class="card-header">Danh sách các ngành trong khoa ${model.name}</h5>
        <div class="card-body">

        </div>
        <div class="table-responsive text-nowrap">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Mã Ngành</th>
                    <th>Tên Ngành</th>
                    <th>Lớp</th>
<%--                    <th>Chức năng</th>--%>
                </tr>
                </thead>
                <tbody class="table-border-bottom-0" id="tableBody">
                <c:forEach var="item" items="${model.academicModelList}" varStatus="loop">
                    <c:url var="editUrl" value="/admin-academic-page">
                        <c:param name="type" value="edit"/>
                        <c:param name="id" value="${item.id}"/>
                    </c:url>
                    <c:url var="deleteUrl" value="/admin-academic-page">
                        <c:param name="type" value="delete"/>
                        <c:param name="id" value="${item.id}"/>
                    </c:url>
                    <c:set var="bg" value="" />
                    <c:if test="${(loop.index + 1) % 6 == 1}">
                        <c:set var="bg" value="primary" />
                    </c:if>
                    <c:if test="${(loop.index + 1) % 6 == 2}">
                        <c:set var="bg" value="success" />
                    </c:if>
                    <c:if test="${(loop.index + 1) % 6 == 3}">
                        <c:set var="bg" value="danger" />
                    </c:if>
                    <c:if test="${(loop.index + 1) % 6 == 4}">
                        <c:set var="bg" value="warning" />
                    </c:if>
                    <c:if test="${(loop.index + 1) % 6 == 5}">
                        <c:set var="bg" value="info" />
                    </c:if>
                    <c:if test="${(loop.index + 1) % 6 == 0}">
                        <c:set var="bg" value="dark" />
                    </c:if>
                    <tr>
                        <td><strong>${loop.index + 1}</strong></td>
                        <td>
                            <span class="badge bg-label-${bg} me-1">${item.code}</span>
                        </td>
                        <td>${item.name}</td>
                        <td><button type="button" class="btn rounded-pill btn-primary">Xem</button></td>
<%--                        <td>--%>
<%--                            <div class="dropdown">--%>
<%--                                <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">--%>
<%--                                    <i class="bx bx-dots-vertical-rounded"></i>--%>
<%--                                </button>--%>
<%--                                <div class="dropdown-menu">--%>
<%--                                    <a class="dropdown-item" href="${editUrl}"><i class="bx bx-edit-alt me-1"></i> Edit</a>--%>
<%--                                    <a class="dropdown-item" href="${deleteUrl}"><i class="bx bx-trash me-1"></i> Delete</a>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </td>--%>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
            <%--            <div class="demo-inline-spacing">--%>
            <%--                <nav aria-label="Page navigation">--%>
            <%--                    <ul class="pagination justify-content-end">--%>
            <%--                        <li class="page-item prev">--%>
            <%--                            <a class="page-link" href="javascript:void(0);"><i class="tf-icon bx bx-chevrons-left"></i></a>--%>
            <%--                        </li>--%>
            <%--                        <li class="page-item">--%>
            <%--                            <a class="page-link" href="javascript:void(0);">1</a>--%>
            <%--                        </li>--%>
            <%--                        <li class="page-item">--%>
            <%--                            <a class="page-link" href="javascript:void(0);">2</a>--%>
            <%--                        </li>--%>
            <%--                        <li class="page-item active">--%>
            <%--                            <a class="page-link" href="javascript:void(0);">3</a>--%>
            <%--                        </li>--%>
            <%--                        <li class="page-item">--%>
            <%--                            <a class="page-link" href="javascript:void(0);">4</a>--%>
            <%--                        </li>--%>
            <%--                        <li class="page-item">--%>
            <%--                            <a class="page-link" href="javascript:void(0);">5</a>--%>
            <%--                        </li>--%>
            <%--                        <li class="page-item next">--%>
            <%--                            <a class="page-link" href="javascript:void(0);"><i class="tf-icon bx bx-chevrons-right"></i></a>--%>
            <%--                        </li>--%>
            <%--                    </ul>--%>
            <%--                </nav>--%>
            <%--            </div>--%>

        </div>
    </div>
</div>
<%--<script>--%>

<%--    $("#btnAdd").on("click",function (e){--%>
<%--        e.preventDefault();--%>
<%--        var data={--%>
<%--            code:$("#idAcademic").val(),--%>
<%--            name:$("#nameAcademic").val(),--%>
<%--            facultyCode:$("#codeFaculty").val()--%>
<%--        }--%>
<%--        console.log(data);--%>
<%--        addAcademic(data);--%>
<%--    })--%>
<%--    function addAcademic(data){--%>
<%--        $.ajax({--%>
<%--            url:'${APIurl}',--%>
<%--            type:'POST',--%>
<%--            contentType: 'application/json',//client send type data to server--%>
<%--            data:JSON.stringify(data),--%>
<%--            dataType:'json',//server send type data to client--%>
<%--            success: function (result) {--%>
<%--                var modal = $('#addFaculyModal');--%>
<%--                modal.modal('hide');--%>
<%--                $('#tableBody').empty();--%>
<%--                for (var i = 0; i < result.length; i++) {--%>
<%--                    addRowToTable(result[i],i);--%>
<%--                }--%>
<%--                // Thêm một hàng mới vào bảng--%>

<%--            },--%>
<%--            error:function(error){--%>
<%--                console.log(error);--%>

<%--            }--%>
<%--        })--%>
<%--    }--%>
<%--    function addRowToTable(academic,index) {--%>
<%--        switch (index % 6) {--%>
<%--            case 0:--%>
<%--                var bg = "primary";--%>
<%--                break;--%>
<%--            case 1:--%>
<%--                var bg = "success";--%>
<%--                break;--%>
<%--            case 2:--%>
<%--                var bg = "danger";--%>
<%--                break;--%>
<%--            case 3:--%>
<%--                var bg = "warning";--%>
<%--                break;--%>
<%--            case 4:--%>
<%--                var bg = "info";--%>
<%--                break;--%>
<%--            case 5:--%>
<%--                var bg = "dark";--%>
<%--                break;--%>
<%--            default:--%>
<%--                var bg="primary";--%>
<%--                break;--%>
<%--        }--%>
<%--        var row = '<tr>' +--%>
<%--            '<td> <strong>' + (index+1) + '</strong></td>' +--%>
<%--            '<td><span class="badge bg-label-'+bg+' me-1">' + academic.code + '</span></td>' +--%>
<%--            '<td>' + academic.name + '</td>' +--%>
<%--            '<td><button type="button" class="btn rounded-pill btn-primary">Xem</button></td>' +--%>
<%--            '<td>' +--%>
<%--            '<div class="dropdown">' +--%>
<%--            '<button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">' +--%>
<%--            '<i class="bx bx-dots-vertical-rounded"></i>' +--%>
<%--            '</button>' +--%>
<%--            '<div class="dropdown-menu">' +--%>
<%--            '<a class="dropdown-item" href="/admin-faculty-page?type=edit&id='+academic.id+'"><i class="bx bx-edit-alt me-1"></i> Edit</a>' +--%>
<%--            '<a class="dropdown-item"  href="/admin-faculty-page?type=delete&id='+academic.id+'"><i class="bx bx-trash me-1"></i> Delete</a>' +--%>
<%--            '</div>' +--%>
<%--            '</div>' +--%>
<%--            '</td>' +--%>
<%--            '</tr>';--%>

<%--        // Append the row to the table body--%>
<%--        $('#tableBody').append(row);--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>
