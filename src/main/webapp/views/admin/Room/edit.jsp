<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 06-Dec-23
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-room"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Phòng /</span>${model.areaCode}${model.roomCode}</h4>
    <div class="bs-toast toast toast-placement-ex m-2 fade hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
        <div class="toast-header">
            <i class="bx bx-bell me-2"></i>
            <div class="me-auto fw-semibold" id="contentToast"></div>
            <%--            <small>11 mins ago</small>--%>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body" id="bodyToast"></div>
    </div>
    <div class="row">
        <div class="col-xl">
            <div class="card mb-4">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Cập nhật phòng</h5>
                    <small class="text-muted float-end">Phòng học của trường</small>
                </div>
                <div class="card-body">
                    <form>
                       <div class="row">
                           <input value="${model.id}" type="hidden" class="form-control" id="idRoom">
                           <div class="mb-3 col-md-4">
                               <label for="codeArea" class="form-label">Khu</label>
                               <select id="codeArea" class="form-select">
                                   <option value="">-- Khu --</option>

                                   <c:forEach begin="1" end="8" varStatus="loop">
                                       <option value="${loop.index}"
                                           ${model.areaCode != null && model.areaCode.equals(Character.toString(Character.toChars(loop.index + 64)[0])) ? 'selected' : ''}   label="<c:out value='${Character.toString(Character.toChars(loop.index + 64)[0])}' />">
                                               ${loop.index}
                                       </option>
                                   </c:forEach>
                               </select>
                           </div>
                           <div class="mb-3 col-md-4">
                               <label class="form-label" for="codeRoom">Mã phòng</label>
                               <input value="${model.roomCode}" type="number" class="form-control" id="codeRoom" required min="101">
                           </div>
                           <div class="mb-3 col-md-4">
                               <label class="form-label" for="numberSeat">Số ghế</label>
                               <input value="${model.seat}" type="number" class="form-control" id="numberSeat" required min="1">
                           </div>
                       </div>

                        <div class="demo-inline-spacing">
                            <button id="btnUpdate" type="button" class="btn btn-primary">Save</button>
                            <a href="<c:url value='/admin-room-page'/>" type="button" class="btn btn-success">Back</a>
                        </div>
                        <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
                        <input type="hidden"  id="selectTypeOpt" value="bg-success">
                        <input type="hidden"  id="selectPlacement" value="top-0 start-50 translate-middle-x">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#btnUpdate").on("click",function (e){
        e.preventDefault();
        var data={
            id:$("#idRoom").val(),
            areaCode:String.fromCharCode(64 + parseInt($("#codeArea").val())),
            roomCode:$("#codeRoom").val(),
            seat:$("#numberSeat").val()
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
            updateRoom(data);
        }
    })
    function updateRoom(data){
        $.ajax({
            url:'${APIurl}',
            type:'PUT',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                $("#selectTypeOpt").val("bg-success");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cập nhật thành công");
                $("#showToastPlacement").click();
            },
            error:function(error){
                console.log(error);
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
