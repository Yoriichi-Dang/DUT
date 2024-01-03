<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 08-Dec-23
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">${model.name} /</span> Cập nhật</h4>
    <div class="row">
        <div class="col-xl">
            <div class="card mb-4">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Cập nhật thông tin chương trình đào tạo</h5>
                </div>
                <div class="card-body">
                    <form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                            <input class="form-control" type="hidden" id="idEducation" name="firstName" value="${model.id}" autofocus="">
                            <div class="mb-3 col-md-6">
                                <label for="codeEducation" class="form-label">Mã chương trình</label>
                                <input   class="form-control" type="text" id="codeEducation" name="firstName" value="${model.code}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="name" class="form-label">Tên chương trình</label>
                                <input class="form-control" type="text" id="name" name="name" value="${model.name}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="typeEducation" class="form-label">Loại</label>
                                <input class="form-control" type="text" id="typeEducation" name="name" value="${model.typeEducation}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="yearStudy" class="form-label">Số kỳ học</label>
                                <input  class="form-control" type="number" id="yearStudy" name="yearStudy" value="${model.semesterStudy}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="AcademicName" class="form-label">Ngành</label>
                                <input  class="form-control" type="text" id="AcademicName" name="yearStudy" value="${model.academicModel.name}">
                            </div>
                        </div>
                        <div class="mt-2">
                            <button type="submit" class="btn btn-primary me-2">Save changes</button>
                            <button onclick="window.location.href='<c:url value='/admin-student-page'/>'" type="reset" class="btn btn-outline-secondary">Back</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
