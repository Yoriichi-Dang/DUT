<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 02-Dec-23
  Time: 9:17 PM
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
    <div class="bs-toast toast toast-placement-ex m-2 fade hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
        <div class="toast-header">
            <i class="bx bx-bell me-2"></i>
            <div class="me-auto fw-semibold" id="contentToast"></div>
            <%--            <small>11 mins ago</small>--%>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body" id="bodyToast"></div>
    </div>
    <input type="hidden"  id="selectTypeOpt" value="bg-success">
    <input type="hidden"  id="selectPlacement" value="top-0 start-50 translate-middle-x">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">${model.name} /</span> Chỉnh sửa</h4>
    <div class="row">
        <div class="col-xl">
            <div class="card mb-4">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Cập nhật thông tin ngành</h5>
                </div>
                <div class="card-body">
                    <form action="#">
                        <input type="hidden" class="form-control" id="idFaculty" value="${model.id}" aria-label="John Doe" aria-describedby="basic-icon-default-fullname2">
                        <div class="mb-3">
                            <label class="form-label" for="codeFaculty">Mã Ngành</label>
                            <div class="input-group input-group-merge">
                                <span id="basic-icon-default-fullname2" class="input-group-text"><i class="bx bx-font"></i></span>
                                <input type="text" class="form-control" id="codeFaculty" placeholder="${model.code}" aria-label="John Doe" aria-describedby="basic-icon-default-fullname2">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="nameFaculty">Tên Ngành</label>
                            <div class="input-group input-group-merge">
                                <span id="basic-icon-default-company2" class="input-group-text"><i class="bx bx-user"></i></span>
                                <input type="text" id="nameFaculty" class="form-control" placeholder="${model.name}" aria-label="ACME Inc." aria-describedby="basic-icon-default-company2">
                            </div>
                        </div>
                        <div class="demo-inline-spacing">
                            <button type="button" id="btnUpdate" class="btn btn-primary">Lưu</button>
                            <a href="<c:url value="/admin-faculty-page"/> " class="btn btn-success">Back</a>
                        </div>
                    </form>
                    <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
                </div>
            </div>
        </div>

    </div>
</div>
<script>
    $("#btnUpdate").on("click",function (e){
        e.preventDefault();
        // $("#showToastPlacement").click();
        var data={
            id:$("#idFaculty").val(),
            code:$("#codeFaculty").val(),
            name:$("#nameFaculty").val()
        }
        updateFaculty(data);
    })
    function updateFaculty(data){
        $.ajax({
            url:'${APIurl}',
            type:'PUT',
            contentType:'application/json',
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success:function(result){
                $("#selectTypeOpt").val("bg-success");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cập nhật thành công");
                $("#bodyToast").text("Cập nhật "+result.name+" thành công");
                $("#showToastPlacement").click();
            },
            error:function(error){
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cập nhật thất bại");
                $("#bodyToast").text("Cập nhật "+result.name+" thất bại");
                $("#showToastPlacement").click();
            }
        })
    }
</script>
</body>
</html>
