<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 08-Dec-23
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-class-course"></c:url>
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
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">${model.name} /</span> Thêm lớp học phần</h4>
    <div class="row">
        <div class="col-xl">
            <div class="card mb-4">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Thêm lớp học phần</h5>
                </div>
                <div class="card-body">
                    <form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                            <input class="form-control" type="hidden" id="idCourse" name="firstName" value="${model.id}" autofocus="">
                            <input class="form-control" type="hidden" id="codeCourse" name="firstName" value="${model.code}" autofocus="">
                            <div class="mb-3 col-md-3">
                                <label for="enrollYear" class="form-label">Khóa</label>
                                <select id="enrollYear" class="form-select" required="">
                                    <c:forEach var="item" begin="21" end="30">
                                        <option value="${item}">${item}</option>
                                    </c:forEach>
                                </select>
                            </div> <div class="mb-3 col-md-3">
                                <label for="startWeek" class="form-label">Tuần học bắt đầu</label>
                                <select id="startWeek" class="form-select" required="">
                                    <c:forEach var="item" begin="1" end="45">
                                        <option value="${item}">${item}</option>
                                    </c:forEach>
                                </select>
                            </div><div class="mb-3 col-md-3">
                                <label for="endWeek" class="form-label">Tuần học kết thúc</label>
                                <select id="endWeek" class="form-select" required="">
                                    <c:forEach var="item" begin="1" end="45">
                                        <option value="${item}">${item}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3 col-md-3">
                                <label for="number" class="form-label">Số lớp tạo</label>
                                <select id="number" class="form-select" required="">
                                    <c:forEach var="item" begin="1" end="10">
                                        <option value="${item}">${item}</option>
                                    </c:forEach>
                                </select>
                            </div>
<%--                            <div class="mb-3 col-md-3">--%>
<%--                                <label for="dayOnWeek" class="form-label">Thứ</label>--%>
<%--                                <select id="dayOnWeek" class="form-select" required="">--%>
<%--                                    <c:forEach var="item" begin="2" end="7">--%>
<%--                                        <option value="${item}">Thứ ${item}</option>--%>
<%--                                    </c:forEach>--%>
<%--                                    <option value="1">Chủ nhật</option>--%>
<%--                                </select>--%>
<%--                            </div>--%>
                            <div class="mb-3 col-md-4">
                                <label for="startLesson" class="form-label">Tiết bắt đầu</label>
                                <select id="startLesson" class="form-select" required="">
                                    <c:forEach var="item" begin="1" end="12">
                                        <option value="${item}">${item}</option>
                                    </c:forEach>
                                </select>
                            </div> <div class="mb-3 col-md-4">
                                <label for="endLesson" class="form-label">Tiết kết thúc</label>
                                <select id="endLesson" class="form-select" required="">
                                    <c:forEach var="item" begin="1" end="12">
                                        <option value="${item}">${item}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="slotRoom" class="form-label">Chỗ</label>
                                <input value="1" name="id" type="number" id="slotRoom" class="form-control"  required min="1">
                        </div>
                            <div class="mb-3 col-md-6">
                                <label for="teacherCode" class="form-label">Giáo viên dạy</label>
                            <select id="teacherCode" class="form-select" required="">
                                <c:forEach var="item" items="${teachers}" varStatus="loop">
                                    <option value="${item.code}">
                                           K.${item.facultyModel.name}  -  ${item.name}
                                    </option>

                                </c:forEach>
                            </select>
                        </div>
                        </div>
                        <div class="mt-2">
                            <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
                            <button id="btnAdd" type="submit" class="btn btn-primary me-2">Save changes</button>
                            <button onclick="window.location.href='<c:url value='/admin-student-page'/>'" type="reset" class="btn btn-outline-secondary">Back</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#btnAdd").on("click",function (e){
        // $("#showToastPlacement").click();
        let startWeek= parseInt($("#startWeek").val());
        let endWeek= parseInt($("#endWeek").val());
        let startLesson=parseInt($("#startLesson").val());
        let endLesson=parseInt($("#endLesson").val());
        if (startWeek < endWeek&&startLesson<endLesson) {
            var data={
                id:$("#idCourse").val(),
                courseCode:$("#codeCourse").val(),
                startWeek:$("#startWeek").val(),
                endWeek:$("#endWeek").val(),
                numberCreate:$("#number").val(),
                enrollYear:$("#enrollYear").val(),
                startLesson:$("#startLesson").val(),
                endLesson:$("#endLesson").val(),
                slotRoom:$("#slotRoom").val(),
                teacherCode:$("#teacherCode").val()
            }
            let message = [];
            let isEmpty = false;  // Biến để kiểm tra xem có thuộc tính rỗng không

            for (let key in data) {
                if (data.hasOwnProperty(key)&&!data[key]) {
                    isEmpty = true;
                    message.push(key);
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
                addClassCourse(data);
            }
        }else{
            $("#selectTypeOpt").val("bg-warning");
            $("#selectPlacement").val("top-0 start-50 translate-middle-x");
            $("#contentToast").text("Tuần bắt đầu học phải nhỏ hơn hoặc bằng tuần kết thúc và tiết học bắt đầu phải nhỏ tiết kết thúc");
            $("#showToastPlacement").click();
        }

    })
    function addClassCourse(data){
        $.ajax({
            url:'${APIurl}',
            type:'POST',
            contentType:'application/json',
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success:function(result){
                $("#selectTypeOpt").val("bg-success");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Thêm thành công");
                $("#showToastPlacement").click();
            },
            error:function(error){
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Thêm thất bại");
                $("#showToastPlacement").click();
            }
        })
    }

</script>
</body>
</html>
