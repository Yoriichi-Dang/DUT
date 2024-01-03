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
<c:url var="APIURl" value="/teacher-schelude-teach-page"></c:url>
<c:url var="APIOffTeachURL" value="/api-off-teach"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Lịch dạy/</span> Danh sách lớp học phần</h4>
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
    <div class="card overflow-hidden mb-4" style="height: 640px;">
        <h5 class="card-header">Lịch dạy</h5>
        <div class="modal fade" id="addFaculyModal" tabindex="-1" style="display: none;" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel1">Thông báo nghỉ học</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="classCourseCode" class="form-label">Mã lớp học phần</label>
                                <input readonly name="content" type="text" id="classCourseCode" class="form-control"  required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="name" class="form-label">Tên học phần</label>
                                <input readonly name="content" type="text" id="name" class="form-control"  required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="room" class="form-label">Phòng</label>
                                <input readonly name="content" type="text" id="room" class="form-control" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="dateOff" class="form-label">Ngày</label>
                                <input readonly name="content" type="date" id="dateOff" class="form-control"  required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                            Hủy
                        </button>
                        <button type="button" class="btn btn-success" id="btnAdd">Xác nhận</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="m-4 mt-1 mb-2 col-sm-3">
            <div class="input-group">
                <input type="date" id="dateInput" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="find">
                <button id="find"  class="btn btn-outline-primary" type="button" >Find</button>
            </div>
        </div>
        <input type="hidden" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addFaculyModal" id="modalButton">
        <div class="card-body vertical-scrollbar" id="vertical-example">
            <div class="table-responsive text-nowrap">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Lớp học phần</th>
                        <th>Môn</th>
                        <th>Phòng</th>
                        <th>Tiết</th>
                        <th>Chức năng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${scheduleClassCourse.list!=null and fn:length(scheduleClassCourse.list)!=0 }">
                        <c:forEach var="item" items="${scheduleClassCourse.list}" varStatus="loop">
                            <c:url var="detailClassCourseUrl" value="/student-class-course-page">
                                <c:param name="type" value="classcourse"/>
                                <c:param name="code" value="${item.code}"/>
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
                                    <span class="badge bg-label-${bg} me-1">${item.classCourseModel.code}</span>
                                </td>
                                <td>
                                        ${item.classCourseModel.courseModel.name}
                                </td>
                                <td>
                                    <span class="badge bg-label-${bg} me-1">${item.classCourseModel.roomModel.areaCode}${item.classCourseModel.roomModel.roomCode}</span>
                                </td>
                                <td>
                                    <c:forEach var="lesson" items="${item.lessons}" varStatus="loop">
                                        <c:choose>
                                            <c:when test="${loop.first}">
                                                ${lesson}-
                                            </c:when>
                                            <c:when test="${loop.last}">
                                                ${lesson}
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </td>
                                <td>
                                    <button type="button" data-name="${item.classCourseModel.courseModel.name}" data-area="${item.classCourseModel.roomModel.areaCode}" data-room="${item.classCourseModel.roomModel.roomCode}" data-date="${scheduleClassCourse.date}" data-code="${item.classCourseModel.code}" class="btn rounded-pill btn-danger btn-off-teach">Nghỉ</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </c:if>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('.btn-off-teach').click(function() {
            let dataCodeValue = $(this).data('code');
            let date=$(this).data('date');
            let area=$(this).data('area');
            let room=$(this).data('room');
            let name=$(this).data('name');
            let roomCode=area+room;
            $("#dateOff").val(date);
            $("#room").val(roomCode);
            $("#classCourseCode").val(dataCodeValue);
            $("#name").val(name);

            let data={
                date:date,
                code:dataCodeValue,
                roomCode:roomCode
            }
            $("#modalButton").click();
            console.log(data);
            $("#btnAdd").click(function(){
                if(date!=null||date!=''){
                    offTeachRegister(data);
                    $("#modalButton").hide();
                }
            });
            // Hiển thị giá trị trong console
        });
        $("#find").click(function() {
            // Lấy giá trị từ trường input
            let selectedDate = $("#dateInput").val();
            let url ='${APIURl}'+'?date='+selectedDate;
            window.location.href = url;
        });
        function offTeachRegister(data){
                    $.ajax({
                        url:'${APIOffTeachURL}',
                        type:'POST',
                        contentType: 'application/json',//client send type data to server
                        data:JSON.stringify(data),
                        dataType:'json',//server send type data to client
                        success: function (result) {
                            $("#selectTypeOpt").val("bg-success");
                            $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                            $("#contentToast").text("Thông báo nghỉ thành công");
                            $("#showToastPlacement").click();
                        },
                        error:function(error){
                            $("#selectTypeOpt").val("bg-danger");
                            $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                            $("#contentToast").text("Thông báo nghỉ  thất bại");
                            $("#showToastPlacement").click();

                        }
                    })
                }
    });

</script>
<%--<script>--%>
<%--    $(".btn-delete").on("click", function() {--%>
<%--        // Retrieve the value of the data-code attribute--%>
<%--        let data={--%>
<%--            code:$(this).data("code")--%>
<%--        }--%>
<%--        // Output the value (you can use it as needed)--%>
<%--        deleteClassCourse(data);--%>
<%--        // Add your logic here, for example, triggering an AJAX request or other actions--%>
<%--    });--%>
<%--    $("#btnAdd").on("click",function (e){--%>
<%--        e.preventDefault();--%>
<%--        let dk=$(this).data("dk");--%>
<%--        let slotRoom=$(this).data("slotRoom");--%>
<%--        if(slotRoom>dk){--%>
<%--            let data={--%>
<%--                code:$(this).data("code")--%>
<%--            }--%>
<%--            addClassCourse(data);--%>
<%--        }--%>
<%--    })--%>
<%--    function addClassCourse(data){--%>
<%--        $.ajax({--%>
<%--            url:'${APIRegisterUrl}',--%>
<%--            type:'POST',--%>
<%--            contentType: 'application/json',//client send type data to server--%>
<%--            data:JSON.stringify(data),--%>
<%--            dataType:'json',//server send type data to client--%>
<%--            success: function (result) {--%>
<%--                window.location.href = "${APIUrl}";--%>
<%--            },--%>
<%--            error:function(error){--%>
<%--                $("#selectTypeOpt").val("bg-danger");--%>
<%--                $("#selectPlacement").val("top-0 start-50 translate-middle-x");--%>
<%--                $("#contentToast").text("Thêm học phần thất bại");--%>
<%--                $("#showToastPlacement").click();--%>
<%--            }--%>
<%--        })--%>
<%--    }--%>
<%--    function deleteClassCourse(data){--%>
<%--        $.ajax({--%>
<%--            url:'${APIRegisterUrl}',--%>
<%--            type:'DELETE',--%>
<%--            contentType: 'application/json',//client send type data to server--%>
<%--            data:JSON.stringify(data),--%>
<%--            dataType:'json',//server send type data to client--%>
<%--            success: function (result) {--%>
<%--                window.location.href = "${APIUrl}";--%>
<%--            },--%>
<%--            error:function(error){--%>
<%--                $("#selectTypeOpt").val("bg-danger");--%>
<%--                $("#selectPlacement").val("top-0 start-50 translate-middle-x");--%>
<%--                $("#contentToast").text("Xóa học phần thất bại");--%>
<%--                $("#showToastPlacement").click();--%>
<%--            }--%>
<%--        })--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>
