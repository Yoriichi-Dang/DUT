<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 08-Dec-23
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-course"></c:url>
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
    <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">${model.name} /</span> Cập nhật</h4>
    <div class="row">
        <div class="col-xl">
            <div class="card mb-4">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Cập nhật học phần</h5>
                </div>
                <div class="card-body">
                    <form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                            <input class="form-control" type="hidden" id="idCourse" name="firstName" value="${model.id}" autofocus="">
                            <div class="mb-3 col-md-6">
                                <label for="codeCourse" class="form-label">Mã học phần</label>
                                <input readonly class="form-control" type="text" id="codeCourse" name="firstName" value="${model.code}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="nameCourse" class="form-label">Tên học phần</label>
                                <input class="form-control" type="text" id="nameCourse" name="nameCourse" value="${model.name}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="numberCredit" class="form-label">Số tín chỉ</label>
                                <input class="form-control" type="number" id="numberCredit" name="name" value="${model.numberCredit}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="pointStudy" class="form-label">Điểm học</label>
                                <input  class="form-control" type="number" id="pointStudy" name="yearStudy" value="${model.pointStudy}">
                            </div> <div class="mb-3 col-md-3">
                                <label for="codeFaculty" class="form-label">Khoa</label>
                                 <select id="codeFaculty" class="form-select" required="">
                                    <c:forEach var="item" items="${faculties}">
                                        <option value="${item.code}" ${model.facultyModel.code==item.code?'selected':''}>${item.name}</option>
                                     </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3 col-md-3">
                                <label for="pointBT" class="form-label">BT</label>
                                <input  class="form-control" type="number" id="pointBT" name="yearStudy" value="${model.pointBT}">
                            </div>
                            <div class="mb-3 col-md-3">
                                <label for="pointBV" class="form-label">BV</label>
                                <input  class="form-control" type="number" id="pointBV" name="yearStudy" value="${model.pointBV}">
                            </div> <div class="mb-3 col-md-3">
                                <label for="pointCK" class="form-label">CK</label>
                                <input  class="form-control" type="number" id="pointCK" name="yearStudy" value="${model.pointCK}">
                            </div> <div class="mb-3 col-md-3">
                                <label for="pointDA" class="form-label">DA</label>
                                <input  class="form-control" type="number" id="pointDA" name="yearStudy" value="${model.pointDA}">
                            </div> <div class="mb-3 col-md-3">
                                <label for="pointGK" class="form-label">GK</label>
                                <input  class="form-control" type="number" id="pointGK" name="yearStudy" value="${model.pointGK}">
                            </div><div class="mb-3 col-md-3">
                                <label for="pointQT" class="form-label">QT</label>
                                <input  class="form-control" type="number" id="pointQT" name="yearStudy" value="${model.pointQT}">
                            </div><div class="mb-3 col-md-3">
                                <label for="pointTH" class="form-label">TH</label>
                                <input  class="form-control" type="number" id="pointTH" name="yearStudy" value="${model.pointTH}">
                            </div>
<%--                            <div class="col-md-4 mb-4">--%>
<%--                                <label for="courseStudyBefore" class="form-label">Học phần học trước</label>--%>
<%--                                <select id="courseStudyBefore" class="form-select" required="" multiple>--%>
<%--                                    <option value="" selected>-- Học phần --</option>--%>
<%--                                    <c:forEach var="item" items="${model.list}">--%>
<%--                                        <option value="${item.code}" >${item.name}</option>--%>
<%--                                    </c:forEach>--%>
<%--                                </select>--%>
<%--                            </div> <div class="col-md-4 mb-4">--%>
<%--                            <label for="courseStudyTogether" class="form-label">Học phần song hành</label>--%>
<%--                            <select id="courseStudyTogether" class="form-select" required="" multiple>--%>
<%--                                <option value="" selected>-- Học phần --</option>--%>
<%--                                <c:forEach var="item" items="${model.list}">--%>
<%--                                    <option value="${item.code}" >${item.name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div> <div class="col-md-4 mb-4">--%>
<%--                            <label for="coursePrerequisite" class="form-label">Học phần tiên quyết</label>--%>
<%--                            <select id="coursePrerequisite" class="form-select" required="" multiple>--%>
<%--                                <option value="" selected>-- Học phần --</option>--%>
<%--                                <c:forEach var="item" items="${model.list}">--%>
<%--                                    <option value="${item.code}" >${item.name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
                        </div>
                        <div class="mt-2">
                            <button id="btnUpdate" type="submit" class="btn btn-primary me-2">Save changes</button>
                            <button onclick="window.location.href='<c:url value='/admin-course-page'/>'" type="reset" class="btn btn-outline-secondary">Back</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    let BT = parseFloat($("#pointBT").val()) || 0; // Chuyển đổi giá trị sang số, mặc định là 0 nếu không hợp lệ
    let BV = parseFloat($("#pointBV").val()) || 0;
    let CK = parseFloat($("#pointCK").val()) || 0;
    let DA = parseFloat($("#pointDA").val()) || 0;
    let GK = parseFloat($("#pointGK").val()) || 0;
    let QT = parseFloat($("#pointQT").val()) || 0;
    let TH = parseFloat($("#pointTH").val()) || 0;
    let total = BT + BV + CK + DA + GK + QT + TH;
    $("#btnUpdate").on("click",function (e){
    e.preventDefault();
    // $("#showToastPlacement").click();
        let data={
            id:$("#idCourse").val(),
            code:$("#codeCourse").val().trim(),
            name:$("#nameCourse").val().trim(),
            facultyCode:$("#codeFaculty").val(),
            numberCredit:parseFloat($("#numberCredit").val())||0,
            pointStudy:parseFloat($("#pointStudy").val())||0,
            pointBT:BT,
            pointBV:BV,
            pointCK:CK,
            pointDA:DA,
            pointGK:GK,
            pointQT:QT,
            pointTH:TH
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
            if(total===1){
                console.log(data);
                updateCourse(data);
            }else{
                $("#selectTypeOpt").val("bg-warning");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Tổng hệ số phải bằng 1");
                $("#bodyToast").text("Tổng hệ số bằng "+total);
                $("#showToastPlacement").click();
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
