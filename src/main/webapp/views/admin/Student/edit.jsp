<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 04-Dec-23
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-student-profile"></c:url>
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
                    <a class="nav-link active" href="<c:url value='/admin-student-page?type=edit&id=${model.id}'/>"><i class="bx bxs-user-account me-1"></i>Thông tin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="pages-account-settings-notifications.html"><i class="bx bx-bell me-1"></i> Kết quả học tập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/admin-student-page?type=changepassword&id=${model.id}'/>"><i class="bx bx-user me-1"></i> Đổi mật khẩu</a>
                </li>
            </ul>
            <div class="card mb-4">
                <h5 class="card-header">Thông tin sinh viên</h5>
                <!-- Account -->
                <div class="card-body">
                    <div class="d-flex align-items-start align-items-sm-center gap-4">
<%--                        <c:url value='/template/admin/assets/img/avatars/avatar.jpg'/>--%>
                        <img src="<c:url value='${model.image!=null?model.image:"/template/admin/assets/img/avatars/avatar.jpg"}'/>" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                        <form id="imageForm" method="post" action="${APIurl}"  enctype="multipart/form-data" class="button-wrapper">
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
                    </div>
                    <div class="demo-vertical-spacing hide">
                        <div class="progress" id="progressUpload">
                            <div class="progress-bar" role="progressbar" style="width: 0%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                               0%
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
                                <label for="codeStudent" class="form-label">Mã sinh viên</label>
                                <input   class="form-control" type="text" id="codeStudent" name="firstName" value="${model.code}" autofocus="">
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
                                <label for="yearStudy" class="form-label">Sinh viên năm</label>
                                <input readonly class="form-control" type="number" id="yearStudy" name="yearStudy" value="${model.yearStudy}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="sex" class="form-label">Giới tính</label>
                                <select id="sex" class="form-select">
                                    <option value="1" ${model.sex!=null&&model.sex=="name"?"selected":""}>Nam</option>
                                    <option value="2" ${model.sex!=null&&model.sex=="nữ"?"selected":""}>Nữ</option>
                                </select>
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="birthday" class="form-label">Sinh nhật</label>
                                <input  class="form-control" type="date" id="birthday" name="birthday" value="${model.birthday}">
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
                                <label for="codeClass" class="form-label">Lớp</label>
                                <select id="codeClass" class="form-select" required>
                                    <c:forEach var="item" items="${classes}">
                                        <option value="${item.code}" ${item.code==model.classCode?"selected":""}>${item.code}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="academic" class="form-label">Ngành</label>
                                <input readonly class="form-control" type="text" id="academic" name="birthday" value="${model.academicModel.name}">
                            </div>
                            <div class="mb-3 col-md-12">
                                <label for="codeEducation" class="form-label">Chương trình đào tạo</label>
                                <select id="codeEducation" class="form-select" required>
                                    educations
                                    <c:forEach var="item" items="${educations.list}">
                                        <option value="${item.code}" ${model.educationModel!=null&&model.educationModel.code==item.code?'selected':''}>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                                <div class="d-flex  col-md-6 mt-3 mb-3">
                                    <div class="flex-shrink-0">
                                        <img src="<c:url value='/template/admin/assets/img/icons/brands/facebook.png'/>" alt="facebook" class="me-3" height="30">
                                    </div>
                                    <div class="flex-grow-1 row">
                                        <div class="col-4 col-sm-4 mb-sm-0 ">
                                            <h6 class="mb-0">Facebook</h6>
                                            <small class="text-muted">
                                                <c:if test="${not empty model.linkFacebook}">
                                                    Connected
                                                </c:if>
                                                <c:if test="${empty model.linkFacebook}">
                                                    Didn't Connected
                                                </c:if>
                                            </small>
                                        </div>
                                        <div class="col-2 col-sm-2 text-end">
                                            <c:if test="${empty model.linkFacebook}">
                                                <button onclick="window.location.href='javascript:void(0);'" type="button" class="btn btn-icon btn-outline-secondary">
                                                    <i class="bx bx-link-alt"></i>
                                                </button>
                                            </c:if>
                                            <c:if test="${not empty model.linkFacebook}">
                                                <button onclick="window.location.href='${model.linkFacebook}'" type="button" class="btn btn-icon btn-outline-secondary">
                                                    <i class="bx bx-link-alt"></i>
                                                </button>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                        </div>
                        <div class="mt-2">
                            <button type="submit" class="btn btn-primary me-2">Save changes</button>
                            <button onclick="window.location.href='<c:url value="/admin-student-page"/>'" type="reset" class="btn btn-outline-secondary">Back</button>
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
    <%--var Upload = function (file) {--%>
    <%--    this.file = file;--%>
    <%--};--%>

    <%--Upload.prototype.getType = function() {--%>
    <%--    return this.file.type;--%>
    <%--};--%>
    <%--Upload.prototype.getSize = function() {--%>
    <%--    return this.file.size;--%>
    <%--};--%>
    <%--Upload.prototype.getName = function() {--%>
    <%--    return this.file.name;--%>
    <%--};--%>
    <%--Upload.prototype.doUpload = function () {--%>
    <%--    var that = this;--%>
    <%--    var formData = new FormData();--%>

    <%--    // add assoc key values, this will be posts values--%>
    <%--    formData.append("file", this.file,this.getName());--%>
    <%--    formData.append("upload_file", true);--%>
    <%--    console.log(this.file);--%>
    <%--    console.log(this.getName());--%>
    <%--    $.ajax({--%>
    <%--        type: "POST",--%>
    <%--        url: "${APIurl}",--%>
    <%--        contentType: "multipart/form-data",//client send type data to server--%>
    <%--        data:JSON.stringify(formData),--%>
    <%--        async: true,--%>
    <%--        dataType:'json',//server send type data to client--%>
    <%--        success: function (data) {--%>
    <%--            console.log("Dữ liệu từ servlet:", data);--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            // handle error--%>
    <%--        }--%>
    <%--    });--%>
    <%--};--%>

    <%--Upload.prototype.progressHandling = function (event) {--%>
    <%--    var percent = 0;--%>
    <%--    var position = event.loaded || event.position;--%>
    <%--    var total = event.total;--%>
    <%--    var progress_bar_id = "#progressUpload";--%>
    <%--    if (event.lengthComputable) {--%>
    <%--        percent = Math.ceil(position / total * 100);--%>
    <%--    }--%>
    <%--    // update progressbars classes so it fits your code--%>
    <%--    $(progress_bar_id + " .progress-bar").css("width", +percent + "%");--%>
    <%--    $(progress_bar_id + " .progress-bar").text(percent + "%");--%>
    <%--};--%>
</script>
</body>
</html>
