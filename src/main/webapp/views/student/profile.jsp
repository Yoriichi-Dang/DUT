<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 06-Dec-23
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-student-profile"></c:url>
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
                    <a class="nav-link active" href="<c:url value='/student-home-page?code=${model.code}'/>"><i class="bx bxs-user-account me-1"></i>Thông tin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bx bx-bell me-1"></i> Kết quả học tập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/student-home-page?type=changepassword&id=${model.id}'/>"><i class="bx bx-user me-1"></i> Đổi mật khẩu</a>
                </li>
            </ul>
            <div class="card mb-4">
                <h5 class="card-header">Thông tin sinh viên</h5>
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
                        <img src="<c:url value='${model.image!=""?model.image:"/template/admin/assets/img/avatars/avatar.jpg"}'/>" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                        <div class="button-wrapper">
<%--                            <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">--%>
<%--                                <span class="d-none d-sm-block">Upload Image</span>--%>
<%--                                <i class="bx bx-upload d-block d-sm-none"></i>--%>
<%--                                <input type="file" id="upload" class="account-file-input" hidden="" accept="image/png, image/jpeg">--%>
<%--                            </label>--%>
<%--                            <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">--%>
<%--                                <i class="bx bx-reset d-block d-sm-none"></i>--%>
<%--                                <span class="d-none d-sm-block">Reset</span>--%>
<%--                            </button>--%>
<%--                            <p class="text-muted mb-0">Ảnh đại diện của sinh viên</p>--%>
                        </div>
                    </div>
                </div>
                <hr class="my-0">
                <div class="card-body">
                    <form id="formAccountSettings">
                        <div class="row">
                            <input class="form-control" type="hidden" id="idStudent" name="id" value="${model.id}" autofocus="">
                            <div class="mb-3 col-md-4">
                                <label for="codeStudent" class="form-label">Mã sinh viên</label>
                                <input readonly  class="form-control" type="text" id="codeStudent" name="code" value="${model.code}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="name" class="form-label">Họ và tên</label>
                                <input readonly class="form-control" type="text" id="name" name="name" value="${model.name}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="cccd" class="form-label">Căn cước công dân</label>
                                <input readonly class="form-control" type="text" id="cccd" name="cccd" value="${model.cccd}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="yearStudy" class="form-label">Sinh viên năm</label>
                                <input readonly class="form-control" type="number" id="yearStudy" name="yearStudy" value="${model.yearStudy}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="sex" class="form-label">Giới tính</label>
                                <input readonly class="form-control" type="text" id="sex" name="sex" value="${model.sex}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="birthday" class="form-label">Sinh nhật</label>
                                <input readonly class="form-control" type="date" id="birthday" name="birthday" value="${model.birthday}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="address" class="form-label">Địa chỉ</label>
                                <input  class="form-control" type="text" id="address" name="address" value="${model.address}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="district" class="form-label">Quận</label>
                                <input  class="form-control" type="text" id="district" name="district" value="${model.district}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="city" class="form-label">Thành Phố</label>
                                <input  class="form-control" type="text" id="city" name="city" value="${model.city}">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="phone" class="form-label">Số điện thoại</label>
                                <div class="input-group input-group-merge">
                                    <span class="input-group-text">VN (+84)</span>
                                    <input  type="text" id="phone" name="phone" class="form-control" value="${model.phone}">
                                </div>
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="email" class="form-label">Email</label>
                                <input  class="form-control" type="email" id="email" name="birthday" value="${model.email}">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="codeClass" class="form-label">Lớp</label>
                                <input readonly class="form-control" type="text" id="codeClass" name="classCode" value="${model.classCode}">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="academic" class="form-label">Ngành</label>
                                <input readonly class="form-control" type="text" id="academic" name="academic" value="${model.academicModel.name}">
                            </div>
                            <div class="mb-3 col-md-12">
                                <label for="codeEducation" class="form-label">Chương trình đào tạo</label>
                                <input readonly class="form-control" type="text" id="codeEducation" name="education" value="${model.academicModel.name}">
                            </div>

                            <div class="mb-3 col-md-12">
                                <label for="linkFace" class="form-label">Facebook</label>
                                <input  class="form-control" type="text" id="linkFace" name="linkFacebook" value="${model.linkFacebook}">
                            </div>

                        </div>
                        <div class="mt-2">
                            <button id="btnUpdate" type="button" class="btn btn-primary me-2">Save changes</button>
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
            id:$("#idStudent").val(),
            code:$("#codeStudent").val(),
            name:$("#name").val(),
            cccd:$("#cccd").val(),
            sex:$("#sex").val(),
            birthday:$("#birthday").val(),
            address:$("#address").val(),
            district:$("#district").val(),
            city:$("#city").val(),
            phone:$("#phone").val(),
            email:$("#email").val(),
            classCode:$("#codeClass").val(),
            linkFacebook:$("#linkFace").val()
        }
        // var message = [];
        // var isEmpty = false;  // Biến để kiểm tra xem có thuộc tính rỗng không
        //
        // for (var key in data) {
        //     if (data.hasOwnProperty(key)) {
        //         if (!data[key]) {
        //             isEmpty = true;
        //             message.push(key);
        //         }
        //     }
        // }
        //
        // if (isEmpty) {
        //     var errorMessage = message.join(', ');  // Chuyển mảng thành chuỗi, sử dụng dấu phẩy làm phân cách
        //     $("#selectTypeOpt").val("bg-warning");
        //     $("#selectPlacement").val("top-0 start-50 translate-middle-x");
        //     $("#contentToast").text("Thiếu trường");
        //     $("#bodyToast").text("Thiếu trường "+errorMessage);
        //     $("#showToastPlacement").click();
        // } else {
        //     updateProfile(data);
        // }
        updateProfile(data);
    })
    function updateProfile(data){
        $.ajax({
            url:'${APIurl}',
            type:'PUT',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                $("#selectTypeOpt").val("bg-success");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cập nhật thông tin thành công");
                $("#showToastPlacement").click();
            },
            error:function(error){
                console.log(error);
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cập nhật thông tin thất bại");
                $("#showToastPlacement").click();
            }
        })
    }
</script>

</body>
</html>
