<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 12-Dec-23
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="APIUrl" value="/student-register-course-page"></c:url>
<c:url var="APIRegisterUrl" value="/api-register-class-course"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Học phần /</span> Đăng ký tín chỉ</h4>
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
    <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block">
    <c:if test="${fn:length(historyRegisterClassCourse.list) != 0}">
        <div class="card">
            <h5 class="card-header">Lớp chọn riêng</h5>

            <div class="card-body">
                <div class="table-responsive text-nowrap">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã lớp học phần</th>
                            <th>Tên lớp học phần</th>
                            <th>Tín chỉ</th>
                            <th>Giảng viên</th>
                            <th>Thời khóa biểu</th>
                            <th>Tuần học</th>
                            <th>Số lượng đăng ký</th>
                            <th>Xóa</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${historyRegisterClassCourse!=null}">
                            <c:forEach var="item" items="${historyRegisterClassCourse.list}" varStatus="loop">
                                <c:set var="bg" value="" />
                                <c:if test="${(loop.index + 1) % 6 == 1}">
                                    <c:set var="bg" value="primary" />
                                </c:if>
                                <c:if test="${(loop.index + 1) % 6 == 2}">
                                    <c:set var="bg" value="success" />
                                </c:if>
                                <c:if test="${(loop.index + 1) % 6 == 3}">
                                    <c:set var="bg" value="danger" />
                                </c:if>
                                <c:if test="${(loop.index + 1) % 6 == 4}">
                                    <c:set var="bg" value="warning" />
                                </c:if>
                                <c:if test="${(loop.index + 1) % 6 == 5}">
                                    <c:set var="bg" value="info" />
                                </c:if>
                                <c:if test="${(loop.index + 1) % 6 == 0}">
                                    <c:set var="bg" value="dark" />
                                </c:if>
                                <tr>
                                    <td>
                                        <strong>${loop.index+1}</strong>
                                    </td>
                                    <td><span class="badge bg-label-${bg} me-1">${item.code}</span></td>
                                    <td>${item.courseModel.name}</td>
                                    <td><span class="badge bg-label-${bg} me-1">${item.courseModel.numberCredit}</span></td>
                                    <td>${item.teacherModel.name}</td>
                                    <td>
                                        <c:if test="${item.dayOnWeek !=0}">
                                           <span class="badge bg-label-${bg} me-1">
                                    <c:if test="${item.dayOnWeek != null and  item.dayOnWeek eq 1}">
                                        Chủ nhật
                                    </c:if>
                                    <c:if test="${item.dayOnWeek != null and item.dayOnWeek != 1}">
                                        Thứ ${item.dayOnWeek}
                                    </c:if>
                                       ,${item.startLesson}-${item.endLesson-1},${item.roomModel.areaCode}${item.roomModel.roomCode}
                                    </span>
                                        </c:if>
                                    </td>
                                    <td>${item.startWeek}-${item.endWeek}</td>
                                    <td>
                                            ${fn:length(item.list)}/${item.slotRoom}
                                    </td>
                                    <td>
                                        <button data-code="${item.code}" type="button" class="btn rounded-pill btn-danger btn-delete">Xóa</button>
                                    </td>
                                </tr>
                            </c:forEach>


                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <hr class="my-5">
    </c:if>
    <div class="card overflow-hidden mb-4" style="height: 340px;">
        <h5 class="card-header">Lớp chọn riêng</h5>
        <div class="card-body vertical-scrollbar" id="vertical-example">
            <div class="table-responsive text-nowrap">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã học phần</th>
                        <th>Tên lớp học phần</th>
                        <th>Tín chỉ</th>
                        <th>Số lớp mở</th>
                        <th>Xem lớp</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${ClassCourseRegister}" varStatus="loop">
                        <c:url var="detailClassCourseUrl" value="/student-register-course-page">
                            <c:param name="type" value="classcourse"/>
                            <c:param name="courseCode" value="${item.courseModel.code}"/>
                        </c:url>
                        <c:set var="bg" value="" />
                        <c:if test="${(loop.index + 1) % 6 == 1}">
                            <c:set var="bg" value="primary" />
                        </c:if>
                        <c:if test="${(loop.index + 1) % 6 == 2}">
                            <c:set var="bg" value="success" />
                        </c:if>
                        <c:if test="${(loop.index + 1) % 6 == 3}">
                            <c:set var="bg" value="danger" />
                        </c:if>
                        <c:if test="${(loop.index + 1) % 6 == 4}">
                            <c:set var="bg" value="warning" />
                        </c:if>
                        <c:if test="${(loop.index + 1) % 6 == 5}">
                            <c:set var="bg" value="info" />
                        </c:if>
                        <c:if test="${(loop.index + 1) % 6 == 0}">
                            <c:set var="bg" value="dark" />
                        </c:if>
                        <tr>
                            <td><strong>${loop.index + 1}</strong></td>
                            <td>
                                <span class="badge bg-label-${bg} me-1">${item.courseModel.code}</span>
                            </td>
                            <td>
                                    ${item.courseModel.name}
                            </td>
                            <td>
                                <span class="badge bg-label-${bg} me-1">${item.courseModel.numberCredit}</span>
                            </td>
                            <td>${fn:length(item.list)}</td>
                            <td>
                                <button type="button" onclick='window.location.href="${detailClassCourseUrl}"' class="btn rounded-pill btn-primary">Chi tiết</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <c:if test="${fn:length(classCourseDetail.list) != 0}">
        <hr class="my-5">
        <div class="card">
            <h5 class="card-header">Danh sách lớp học phần</h5>
            <div class="card-body">
                <div class="table-responsive text-nowrap">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã lớp học phần</th>
                            <th>Tên lớp học phần</th>
                            <th>Giảng viên</th>
                            <th>Tuần học</th>
                            <th>Thời khóa biểu</th>
                            <th>SL</th>
                            <th>ĐK</th>
                            <th>Dự bị</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${classCourseDetail.list}" varStatus="loop">
                            <c:set var="bg" value="" />
                            <c:if test="${(loop.index + 1) % 6 == 1}">
                                <c:set var="bg" value="primary" />
                            </c:if>
                            <c:if test="${(loop.index + 1) % 6 == 2}">
                                <c:set var="bg" value="success" />
                            </c:if>
                            <c:if test="${(loop.index + 1) % 6 == 3}">
                                <c:set var="bg" value="danger" />
                            </c:if>
                            <c:if test="${(loop.index + 1) % 6 == 4}">
                                <c:set var="bg" value="warning" />
                            </c:if>
                            <c:if test="${(loop.index + 1) % 6 == 5}">
                                <c:set var="bg" value="info" />
                            </c:if>
                            <c:if test="${(loop.index + 1) % 6 == 0}">
                                <c:set var="bg" value="dark" />
                            </c:if>
                            <tr>
                                <td>
                                    <strong>${loop.index+1}</strong>
                                </td>
                                <td><span class="badge bg-label-${bg} me-1">${item.code}</span></td>
                                <td>${classCourseDetail.courseModel.name}</td>
                                <td>${item.teacherModel.name}</td>
                                <td>${item.startWeek}-${item.endWeek}</td>
                                <td>
                                    <c:if test="${item.dayOnWeek !=0}">
                                          <span class="badge bg-label-${bg} me-1">
                                    <c:if test="${item.dayOnWeek != null and  item.dayOnWeek eq 1}">
                                        Chủ nhật
                                    </c:if>
                                    <c:if test="${item.dayOnWeek != null and item.dayOnWeek != 1}">
                                        Thứ ${item.dayOnWeek}
                                    </c:if>
                                       ,${item.startLesson}-${item.endLesson-1},${item.roomModel.areaCode}${item.roomModel.roomCode}
                                    </span>
                                    </c:if>
                                </td>
                                <td>
                                   ${item.slotRoom}
                                </td>
                                <td>
                                        ${fn:length(item.list)}
                                </td>
                                <td>
                                    0
                                </td>
                                <td>
                                    <button type="button" class="btn rounded-pill btn-danger">Dự bị</button>
                                    <button data-slotRoom="${item.slotRoom}" data-dk="${fn:length(item.list)}" data-code="${item.code}" id="btnAdd" type="button" class="btn rounded-pill btn-primary">Chọn</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
</div>
<script>
    $(".btn-delete").on("click", function() {
        // Retrieve the value of the data-code attribute
        let data={
            code:$(this).data("code")
        }
        // Output the value (you can use it as needed)
        deleteClassCourse(data);
        // Add your logic here, for example, triggering an AJAX request or other actions
    });
    $("#btnAdd").on("click",function (e){
        e.preventDefault();
        let dk=$(this).data("dk");
        let slotRoom=$(this).data("slotroom");
        if(slotRoom>dk){
            let data={
                code:$(this).data("code")
            }
            addClassCourse(data);
        }
    })
    function addClassCourse(data){
        $.ajax({
            url:'${APIRegisterUrl}',
            type:'POST',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                window.location.href = "${APIUrl}";
            },
            error:function(error){
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Thêm học phần thất bại");
                $("#showToastPlacement").click();
            }
        })
    }
    function deleteClassCourse(data){
        $.ajax({
            url:'${APIRegisterUrl}',
            type:'DELETE',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                window.location.href = "${APIUrl}";
            },
            error:function(error){
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Xóa học phần thất bại");
                $("#showToastPlacement").click();
            }
        })
    }
</script>
</body>
</html>
