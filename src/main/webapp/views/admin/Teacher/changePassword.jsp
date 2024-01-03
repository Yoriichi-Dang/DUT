<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 04-Dec-23
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-teacher-account"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Account Settings /</span> Account</h4>

    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-pills flex-column flex-md-row mb-3">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/admin-teacher-page?type=edit&id=${model.id}'/>"><i class="bx bxs-user-account me-1"></i>Thông tin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bx bx-bell me-1"></i> Kết quả học tập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value='/admin-teacher-page?type=changepassword&id=${model.id}'/>"><i class="bx bx-user me-1"></i> Đổi mật khẩu</a>
                </li>
            </ul>
            <div class="card mb-4">
                <h5 class="card-header">Cấp lại mật khẩu</h5>
                <div class="bs-toast toast toast-placement-ex m-2 fade hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
                    <div class="toast-header">
                        <i class="bx bx-bell me-2"></i>
                        <div class="me-auto fw-semibold" id="contentToast"></div>
                        <%--            <small>11 mins ago</small>--%>
                        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                    <div class="toast-body" id="bodyToast"></div>
                </div>
                <!-- Account -->
                <div class="card-body">
                    <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img src="<c:url value='${model.image!=null?model.image:"/template/admin/assets/img/avatars/avatar.jpg"}'/>" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                    </div>
                </div>
                <hr class="my-0">
                <div class="card-body">
                    <form action="#" method="POST">
                        <div class="row">
                            <input class="form-control" type="hidden" id="idStudent" name="firstName" value="${model.id}" autofocus="">
                            <div class="mb-3 col-md-6">
                                <label for="username" class="form-label">Mã giáo viên</label>
                                <input readonly class="form-control" type="text" id="username" name="codeStudent" value="${model.code}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="password" class="form-label">Password</label>
                                <input class="form-control" type="text" id="password" name="password" placeholder="Nhập mật khẩu mới" value="" autofocus="">
                            </div>
                        </div>
                        <div class="mt-2">
                            <button id="btnUpdate" type="submit" class="btn btn-primary me-2">Save</button>
                            <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
                            <input type="hidden"  id="selectTypeOpt" value="bg-success">
                            <input type="hidden"  id="selectPlacement" value="top-0 start-50 translate-middle-x">
                        </div>
                    </form>
                </div>
                <!-- /Account -->
            </div>
        </div>
    </div>
</div>
<script>
    $("#btnUpdate").on("click",function (e){
        e.preventDefault();
        var data={
            username:$("#username").val(),
            password:$("#password").val()
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
            updatePassword(data);
        }
    })
    function updatePassword(data){
        $.ajax({
            url:'${APIurl}',
            type:'PUT',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                $("#selectTypeOpt").val("bg-success");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cấp lại mật khẩu thành công");
                $("#showToastPlacement").click();
            },
            error:function(error){
                console.log(error);
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cấp mật khẩu thất bại");
                $("#showToastPlacement").click();
            }
        })
    }
</script>

</body>
</html>
