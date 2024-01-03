<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 04-Dec-23
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-teacher-profile"></c:url>
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
                    <a class="nav-link active" href="<c:url value='/admin-teacher-page?type=edit&id=${model.id}'/>"><i class="bx bxs-user-account me-1"></i>Thông tin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bx bx-bell me-1"></i> Kết quả học tập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/admin-teacher-page?type=changepassword&id=${model.id}'/>"><i class="bx bx-user me-1"></i> Đổi mật khẩu</a>
                </li>
            </ul>
            <div class="card mb-4">
                <h5 class="card-header">Thông tin giáo viên</h5>
                <!-- Account -->
                <div class="card-body">
                    <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img src="<c:url value='${model.image!=null?model.image:"/template/admin/assets/img/avatars/avatar.jpg"}'/>" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                        <form  id="imageForm" method="post" action="${APIurl}"  enctype="multipart/form-data" class="button-wrapper">
                            <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">
                                <span class="d-none d-sm-block">Upload new photo</span>
                                <i class="bx bx-upload d-block d-sm-none"></i>
                                <input multiple name="file" type="file" id="upload" class="account-file-input" hidden="" accept="image/png, image/jpeg">
                            </label>
                            <input class="form-control" type="hidden"  name="id" value="${model.id}" autofocus="">
                            <button id="btnUpdateImage" type="button" class="btn btn-outline-secondary account-image-reset mb-4">
                                <i class="bx bx-reset d-block d-sm-none"></i>
                                <span class="d-none d-sm-block">Save Image</span>
                            </button>

                            <p class="text-muted mb-0">Allowed JPG, GIF or PNG. Max size of 800K</p>
                        </form>
                        <div class="demo-vertical-spacing hide">
                            <div class="progress" id="progressUpload">
                                <div class="progress-bar" role="progressbar" style="width: 0%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                                    0%
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="my-0">
                <div class="card-body">
                    <form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                            <input class="form-control" type="hidden" id="idStudent" name="firstName" value="${model.id}" autofocus="">
                            <div class="mb-3 col-md-4">
                                <label for="codeTeacher" class="form-label">Mã Giáo viên</label>
                                <input   class="form-control" type="text" id="codeTeacher" name="firstName" value="${model.code}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="name" class="form-label">Họ và tên</label>
                                <input class="form-control" type="text" id="name" name="name" value="${model.name}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="cccd" class="form-label">Căn cước công dân</label>
                                <input class="form-control" type="text" id="cccd" name="name" value="${model.cccd}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="sex" class="form-label">Giới tính</label>
                                <select id="sex" class="form-select">
                                    <option value="1" ${model.sex!=null&&model.sex=="name"?"selected":""}>Nam</option>
                                    <option value="2" ${model.sex!=null&&model.sex=="nữ"?"selected":""}>Nữ</option>
                                </select>
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="birthday" class="form-label">Ngày sinh</label>
                                <input  class="form-control" type="date" id="birthday" name="birthday" value="${model.birthday}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="hometown" class="form-label">Quê quán</label>
                                <input readonly class="form-control" type="text" id="hometown" name="birthday" value="${model.hometown}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="address" class="form-label">Địa chỉ</label>
                                <input readonly class="form-control" type="text" id="address" name="birthday" value="${model.address}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="district" class="form-label">Quận</label>
                                <input readonly class="form-control" type="text" id="district" name="birthday" value="${model.district}">
                            </div>
                            <div class="mb-3 col-md-4">
                            <label for="city" class="form-label">Thành Phố</label>
                            <input readonly class="form-control" type="text" id="city" name="birthday" value="${model.city}">
                         </div>
                            <div class="mb-3 col-md-6">
                                <label for="phone" class="form-label">Số điện thoại</label>
                                <div class="input-group input-group-merge">
                                    <span class="input-group-text">VN (+84)</span>
                                    <input readonly type="text" id="phone" name="phoneNumber" class="form-control" value="${model.phone}">
                                </div>
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="email" class="form-label">Email</label>
                                <input readonly class="form-control" type="email" id="email" name="birthday" value="${model.email}">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="level" class="form-label">Trình độ</label>
                                <select id="level" class="form-select" required>
                                        <option value="" selected>-- Trình độ --</option>
                                        <option value="1" ${model.level eq 'Cử nhân'?'selected':''}>Cử nhân</option>
                                        <option value="2" ${model.level eq 'Thạc sĩ'?'selected':''}>Thạc sĩ</option>
                                        <option value="3" ${model.level eq 'Tiến sĩ'?'selected':''}>Tiến sĩ</option>
                                        <option value="4" ${model.level eq 'Phó giáo sư'?'selected':''}>Phó giáo sư</option>
                                        <option value="5" ${model.level eq 'Giáo sư'?'selected':''}>Giáo sư</option>
                                </select>
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="codeClass" class="form-label">Khoa</label>
                                <select id="codeClass" class="form-select" required>
                                    <c:forEach var="item" items="${faculties}">
                                        <option value="${item.code}" ${item.code==model.facultyCode?"selected":""}>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="mt-2">
                            <button type="submit" class="btn btn-primary me-2">Save changes</button>
                            <button onclick="window.location.href='<c:url value="/admin-teacher-page"/>'" type="reset" class="btn btn-outline-secondary">Back</button>
                        </div>
                    </form>
                </div>
                <!-- /Account -->
            </div>
            <div class="card">
                <h5 class="card-header">Delete Account</h5>
                <div class="card-body">
                    <div class="mb-3 col-12 mb-0">
                        <div class="alert alert-warning">
                            <h6 class="alert-heading fw-bold mb-1">Are you sure you want to delete your account?</h6>
                            <p class="mb-0">Once you delete your account, there is no going back. Please be certain.</p>
                        </div>
                    </div>
                    <form id="formAccountDeactivation" onsubmit="return false">
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" name="accountActivation" id="accountActivation">
                            <label class="form-check-label" for="accountActivation">I confirm my account deactivation</label>
                        </div>
                        <button type="submit" class="btn btn-danger deactivate-account">Deactivate Account</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var file;
    $("#upload").on("change", function (e) {
        file = $(this)[0].files[0];

        // maby check size or type here with upload.getSize() and upload.getType()

        // execute upload
    });
    $("#btnUpdateImage").on("click",function (e){
        e.preventDefault();
        if(file!=null){
            $("#imageForm").submit();
        }

    })
</script>
</body>
</html>
