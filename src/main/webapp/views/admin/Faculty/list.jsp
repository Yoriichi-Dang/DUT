<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 02-Dec-23
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-faculty"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Đại học /</span> Khoa</h4>
    <div class="card">
        <h5 class="card-header">Danh sách các khoa</h5>
        <div class="card-body">
            <div class="row gy-3">
                <div class="bs-toast toast toast-placement-ex m-2 fade hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
                    <div class="toast-header">
                        <i class="bx bx-bell me-2"></i>
                        <div class="me-auto fw-semibold" id="contentToast"></div>
                        <%--            <small>11 mins ago</small>--%>
                        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                    <div class="toast-body" id="bodyToast"></div>
                </div>
                <!-- Default Modal -->
                <div class="col-lg-4 col-md-6">
                    <div class="mt-0">
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addFaculyModal">
                            Thêm Khoa
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="addFaculyModal" tabindex="-1" style="display: none;" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel1">Thêm Mới Khoa</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col mb-3">
                                                <label for="idFaculty" class="form-label">Mã khoa</label>
                                                <input name="id" type="text" id="idFaculty" class="form-control" placeholder="Nhập mã khoa" required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col mb-3">
                                                <label for="nameFaculty" class="form-label">Tên Khoa</label>
                                                <input name="name" type="text" id="nameFaculty" class="form-control" placeholder="Nhập tên khoa" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                            Đóng
                                        </button>
                                        <button type="button" class="btn btn-primary" id="btnAdd">Thêm</button>
                                        <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
                                        <input type="hidden"  id="selectTypeOpt" value="bg-success">
                                        <input type="hidden"  id="selectPlacement" value="top-0 start-50 translate-middle-x">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="table-responsive text-nowrap">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Mã khoa</th>
                    <th>Tên Khoa</th>
                    <th>Ngành</th>
                    <th>Chức năng</th>
                </tr>
                </thead>
                <tbody class="table-border-bottom-0" id="tableBody">
                <c:forEach var="item" items="${model.list}" varStatus="loop">
                    <c:url var="editUrl" value="/admin-faculty-page">
                        <c:param name="type" value="edit"/>
                        <c:param name="id" value="${item.id}"/>
                    </c:url>
                    <c:url var="deleteUrl" value="/admin-faculty-page">
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
                        <td><a href="<c:url value="/admin-faculty-page?type=listacademic&id=${item.id}"/>" class="btn rounded-pill btn-primary">Xem</a></td>
                        <td>
                            <div class="dropdown">
                                <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                    <i class="bx bx-dots-vertical-rounded"></i>
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="${editUrl}"><i class="bx bx-edit-alt me-1"></i> Edit</a>
                                    <a class="dropdown-item" href="${deleteUrl}"><i class="bx bx-trash me-1"></i> Delete</a>
                                </div>
                            </div>
                        </td>
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
<script>

    $("#btnAdd").on("click",function (e){
        e.preventDefault();
        var data={
            code:$("#idFaculty").val(),
            name:$("#nameFaculty").val()
        }
        var message = [];
        var isEmpty = false;  // Biến để kiểm tra xem có thuộc tính rỗng không

        for (var key in data) {
            if (data.hasOwnProperty(key)) {
                if (!data[key]) {
                    isEmpty = true;
                    message.push(key);
                }
            }
        }

        if (isEmpty) {
            var errorMessage = message.join(', ');  // Chuyển mảng thành chuỗi, sử dụng dấu phẩy làm phân cách
            $("#selectTypeOpt").val("bg-warning");
            $("#selectPlacement").val("top-0 start-50 translate-middle-x");
            $("#contentToast").text("Thiếu trường");
            $("#bodyToast").text("Thiếu trường "+errorMessage);
            $("#showToastPlacement").click();
        } else {
            addFaculty(data);
        }
    })
    function addFaculty(data){
        $.ajax({
            url:'${APIurl}',
            type:'POST',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                var modal = $('#addFaculyModal');
                modal.modal('hide');
                $('#tableBody').empty();
                for (var i = 0; i < result.length; i++) {
                    addRowToTable(result[i],i);
                }
                // Thêm một hàng mới vào bảng

            },
            error:function(error){
                console.log(error);

            }
        })
    }
    function addRowToTable(faculty,index) {
        switch (index % 6) {
            case 0:
                var bg = "primary";
                break;
            case 1:
                var bg = "success";
                break;
            case 2:
                var bg = "danger";
                break;
            case 3:
                var bg = "warning";
                break;
            case 4:
                var bg = "info";
                break;
            case 5:
                var bg = "dark";
                break;
            default:
                var bg="primary";
                break;
        }
        var linkAcademicList = '/DUT/admin-faculty-page?type=listacademic&id=' + faculty.id;
        var linkDelete = '/DUT/admin-faculty-page?type=delete&id=' + faculty.id;
        var linkEdit = '/DUT/admin-faculty-page?type=edit&id=' + faculty.id;

        var row = '<tr>' +
            '<td> <strong>' + (index + 1) + '</strong></td>' +
            '<td><span class="badge bg-label-' + bg + ' me-1">' + faculty.code + '</span></td>' +
            '<td>' + faculty.name + '</td>' +
            '<td><a href="' + linkAcademicList + '" class="btn rounded-pill btn-primary">Xem</a></td>' +
            '<td>' +
            '<div class="dropdown">' +
            '<button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">' +
            '<i class="bx bx-dots-vertical-rounded"></i>' +
            '</button>' +
            '<div class="dropdown-menu">' +
            '<a class="dropdown-item" href="' + linkEdit + '"><i class="bx bx-edit-alt me-1"></i> Edit</a>' +
            '<a class="dropdown-item" href="' + linkDelete + '"><i class="bx bx-trash me-1"></i> Delete</a>' +
            '</div>' +
            '</div>' +
            '</td>' +
            '</tr>';
        // Append the row to the table body
        $('#tableBody').append(row);
    }
</script>
</body>
</html>
