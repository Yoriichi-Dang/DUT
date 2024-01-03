<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 08-Dec-23
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-dependent-course"></c:url>
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
          <h5 class="mb-0">Cập nhật học phần phụ thuộc</h5>
        </div>
        <div class="card-body">
          <form id="formAccountSettings" method="POST" onsubmit="return false">
            <div class="row">
              <input class="form-control" type="hidden" id="idCourse" name="firstName" value="${model.id}" autofocus="">
              <div class="mb-3 col-md-6">
                <label for="nameCourse" class="form-label">Tên học phần</label>
                <input readonly class="form-control" type="text" id="nameCourse" name="nameCourse" value="${model.name}" autofocus="">
              </div>
              <div class="mb-3 col-md-6">
                <label for="typeCourse" class="form-label">Loại phụ thuộc</label>
                <select class="form-select" id="typeCourse" aria-label="Example select with button addon">
                    <option value="courseStudyBefore" ${model.typeCourse!=null&&model.typeCourse eq 'courseStudyBefore'?'selected':'' }>Học trước</option>
                    <option value="courseStudyTogether" ${model.typeCourse!=null&&model.typeCourse eq 'courseStudyTogether'?'selected':'' }>Song hành</option>
                    <option value="coursePrerequisite"  ${model.typeCourse!=null&&model.typeCourse eq 'coursePrerequisite '?'selected':'' }>Tiên quyết</option>
                </select>
              </div>
            </div>
            <div class="mt-2">
              <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
              <button id="btnUpdate" type="submit" class="btn btn-primary me-2">Save changes</button>
              <button onclick="window.location.href='<c:url value='/admin-course-page?type=dependentcourse&code=${model.code}'/>'" type="reset" class="btn btn-outline-secondary">Back</button>
            </div>
          </form>
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
      id:$("#idCourse").val(),
      typeCourse:$("#typeCourse").val()
    }
    let message = [];
    let isEmpty = false;  // Biến để kiểm tra xem có thuộc tính rỗng không

    for (let key in data) {
      if (data.hasOwnProperty(key)) {
        if (!data[key]&&Number(data[key]) !== 0) {
          isEmpty = true;
          message.push(key);
        }
      }
    }
    if(isEmpty){
      $("#selectTypeOpt").val("bg-warning");
      $("#selectPlacement").val("top-0 start-50 translate-middle-x");
      $("#contentToast").text("Thiếu trường "+message);
      $("#showToastPlacement").click();
    }else{
      updateCourse(data);
    }
  })
  function updateCourse(data){
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
        $("#showToastPlacement").click();
      },
      error:function(error){
        $("#selectTypeOpt").val("bg-danger");
        $("#selectPlacement").val("top-0 start-50 translate-middle-x");
        $("#contentToast").text("Cập nhật thất bại");
        $("#showToastPlacement").click();
      }
    })
  }

</script>
</body>
</html>
