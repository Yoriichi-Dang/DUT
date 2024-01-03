<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 06-Dec-23
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-student-account"></c:url>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Tài khoản /</span> ${model.name}</h4>

    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-pills flex-column flex-md-row mb-3">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/student-home-page?code=${model.code}'/>"><i class="bx bxs-user-account me-1"></i>Thông tin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bx bx-bell me-1"></i> Kết quả học tập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value='/student-home-page?type=changepassword&id=${model.id}'/>"><i class="bx bx-user me-1"></i> Đổi mật khẩu</a>
                </li>
            </ul>
            <div class="card mb-4">
                <h5 class="card-header">Thay đổi mật khẩu</h5>
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
                            <div class="mb-3 col-md-4">
                                <label for="username" class="form-label">Mã sinh viên</label>
                                <input readonly class="form-control" type="text" id="username" name="codeStudent" value="${model.code}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="password" class="form-label">Mật khẩu cũ</label>
                                <input class="form-control" type="text" id="password" name="password" placeholder="Nhập mật khẩu cũ"  autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="newPassword" class="form-label">Mật khẩu mới</label>
                                <input class="form-control" type="text" id="newPassword" name="password" placeholder="Nhập mật khẩu mới"  autofocus="">
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
            password:$("#password").val(),
            newPassword:$("#newPassword").val()
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
                $("#contentToast").text("Cập nhật mật khẩu thành công");
                $("#bodyToast").text("");
                $("#password").val("");
                $("#newPassword").val("");
                $("#showToastPlacement").click();
            },
            error:function(error){
                console.log(error);
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cập nhật mật khẩu thất bại");
                $("#bodyToast").text("");
                $("#showToastPlacement").click();
            }
        })
    }
</script>
</body>
</html>
