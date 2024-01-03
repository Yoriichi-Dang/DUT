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
<c:url var="APIURl" value="/api-register-class-course-teach"></c:url>
<html>
<head>
  <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
  <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Lớp học phần/</span> Danh sách lớp học phần</h4>
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
  <div class="card overflow-hidden mb-4" style="height: 652px;">
    <h5 class="card-header">Lớp học phần dạy</h5>
    <div class="modal fade" id="addFaculyModal" tabindex="-1" style="display: none;" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel1">Thông báo dạy bù</h5>
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
                <label for="name" class="form-label">Ngày</label>
                <div class="input-group mt-0">
                  <input type="date" id="dateInput" class="form-control"  placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="find">
                  <button id="find"  class="btn btn-outline-primary" type="button" >Find</button>
                </div>
<%--                <label for="dateRegister" class="form-label">Ngày</label>--%>
<%--                <input  name="content" type="date" id="dateRegister" class="form-control"  required>--%>
              </div>
              <div class="col-md-6 mb-3">
                <label for="roomCode" class="form-label">Phòng học trống</label>
                <select id="roomCode" class="form-select" required="">

                </select>
              </div>
              <div class="col-md-6 mb-3">
                <label for="startLesson" class="form-label">Tiết bắt đầu</label>
                <select id="startLesson" class="form-select" required="">
                  <c:forEach var="item" begin="1" end="11">
                    <option value="${item}">${item}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="col-md-6 mb-3">
                <label for="endLesson" class="form-label">Tiết kết thúc</label>
                <select id="endLesson" class="form-select" required="">
                  <c:forEach var="item" begin="1" end="12">
                    <option value="${item}">${item}</option>
                  </c:forEach>
                </select>
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
          <c:if test="${classcourses!=null and fn:length(classcourses)!=0 }">
            <c:forEach var="item" items="${classcourses}" varStatus="loop">
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
                  <span class="badge bg-label-${bg} me-1">${item.code}</span>
                </td>
                <td>
                    ${item.courseModel.name}
                </td>
                <td>
                  <span class="badge bg-label-${bg} me-1">${item.roomModel.areaCode}${item.roomModel.roomCode}</span>
                </td>
                <td>
                    ${item.startLesson}-${item.endLesson}
                </td>
                <td>
                  <button type="button" data-seat="${item.roomModel.seat}" data-name="${item.courseModel.name}" data-code="${item.code}" class="btn rounded-pill btn-primary btn-off-teach">Dạy bù</button>
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
      let name=$(this).data('name');
      let slot=parseInt($(this).data('seat'));

      $("#classCourseCode").val(dataCodeValue);
      $("#name").val(name);
      $("#modalButton").click();
      $("#btnAdd").click(function(){
        let startLesson=parseInt($("#startLesson").val());
        let endLesson=parseInt($("#endLesson").val());
        if(startLesson<endLesson){
           let seatValue = parseInt($("#roomCode option:selected").data("seat"));
           if(slot<=seatValue){
             $("#modalButton").hide();
             let date=$("#dateInput").val();
             let roomCode=$("#roomCode").val()
             if(date!=null){
               let data={
                 date:date,
                 code:dataCodeValue,
                 roomCode:roomCode,
                 startLesson:$("#startLesson").val(),
                 endLesson:$("#endLesson").val()
               }
               console.log(data);
               registerTeach(data);
             }
           else{
               $("#selectTypeOpt").val("bg-warning");
               $("#selectPlacement").val("top-0 start-50 translate-middle-x");
               $("#contentToast").text("Nhập ngày đăng ký ");
               $("#showToastPlacement").click();
             }
           }else{
             $("#selectTypeOpt").val("bg-warning");
             $("#selectPlacement").val("top-0 start-50 translate-middle-x");
             $("#contentToast").text("Phòng không đủ chỗ "+seatValue);
             $("#showToastPlacement").click();
           }

        }else{
          $("#selectTypeOpt").val("bg-warning");
          $("#selectPlacement").val("top-0 start-50 translate-middle-x");
          $("#contentToast").text("Tiết bắt đầu phải bé hơn tiết kết thúc");
          $("#showToastPlacement").click();
        }
      });
      // Hiển thị giá trị trong console
    });
    $("#find").click(function() {
      // Lấy giá trị từ trường input
      let selectedDate = $("#dateInput").val();
      let startLesson=parseInt($("#startLesson").val());
      let endLesson=parseInt($("#endLesson").val());

      if(selectedDate!==''){
        if(startLesson<endLesson){
          let data={
            date:selectedDate,
            startLesson:startLesson,
            endLesson:endLesson
          }
          console.log((data));
          findRoomEmptyInDate(data);
        }else{
          $("#selectTypeOpt").val("bg-warning");
          $("#selectPlacement").val("top-0 start-50 translate-middle-x");
          $("#contentToast").text("Tiết bắt đầu phải bé hơn tiết kết thúc");
          $("#showToastPlacement").click();
        }
      }else{
        $("#selectTypeOpt").val("bg-warning");
        $("#selectPlacement").val("top-0 start-50 translate-middle-x");
        $("#contentToast").text("Nhập ngày");
        $("#showToastPlacement").click();

      }
    });
    function findRoomEmptyInDate(data){
      $.ajax({
        url:'${APIURl}',
        type:'GET',
        contentType: 'application/json',//client send type data to server
        data:data,
        dataType:'json',//server send type data to client
        success: function (result) {
          $('#roomCode').empty();
          let row;
          for (var i = 0; i < result.length; i++) {
            console.log(result[i]);
            row='<option data-seat="'+result[i].seat+'" value="'+result[i].areaCode+result[i].roomCode+'">'+result[i].areaCode+result[i].roomCode+'</option>';
            $("#roomCode").append(row);
          }
        },
        error:function(error){
          $("#selectTypeOpt").val("bg-danger");
          $("#selectPlacement").val("top-0 start-50 translate-middle-x");
          $("#contentToast").text("Tìm lớp thất bại");
          $("#showToastPlacement").click();

        }
      })
    }
    function registerTeach(data){
      $.ajax({
        url:'${APIURl}',
        type:'POST',
        contentType: 'application/json',//client send type data to server
        data:JSON.stringify(data),
        dataType:'json',//server send type data to client
        success: function (result) {
          $("#selectTypeOpt").val("bg-success");
          $("#selectPlacement").val("top-0 start-50 translate-middle-x");
          $("#contentToast").text("Đăng ký lớp thành công");
          $("#showToastPlacement").click();
        },
        error:function(error){
          $("#selectTypeOpt").val("bg-danger");
          $("#selectPlacement").val("top-0 start-50 translate-middle-x");
          $("#contentToast").text("Đăng ký lớp thất bại");
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
