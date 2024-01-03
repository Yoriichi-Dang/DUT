<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 02-Dec-23
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/admin-class-course-page"></c:url>
<html>
<head>
  <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
  <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">${model.courseModel.name}/</span> Danh sách lớp học phần</h4>
  <div class="card">
    <h5 class="card-header">Danh sách các lớp học phần</h5>
    <div class="card-body">
      <div class="row gy-3">
        <div class="bs-toast toast toast-placement-ex m-2 fade hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
          <div class="toast-header">
            <i class="bx bx-bell me-2"></i>
            <div class="me-auto fw-semibold" id="contentToast"></div>
            <%--            <small>11 mins ago</small>--%>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
          </div>
          <div class="toast-body" id="bodyToast"></div>
        </div>
        <!-- Default Modal -->
      </div>
    </div>
    <div class="table-responsive text-nowrap">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>STT</th>
          <th>Mã lớp học phần</th>
          <th>Ngày trong tuần</th>
          <th>Phòng</th>
          <th>Tiết</th>
          <th>Tuần học</th>
          <th>Số chỗ</th>
          <th>Giáo viên</th>
          <th>Chức năng</th>
        </tr>
        </thead>
        <tbody class="table-border-bottom-0" id="tableBody">
        <c:forEach var="item" items="${model.list}" varStatus="loop">
          <c:url var="classcourseUrl" value="/admin-class-course-page">
            <c:param name="type" value="classcourse"/>
            <c:param name="code" value="${item.code}"/>
          </c:url>
          <c:url var="addClassUrl" value="/admin-class-course-page">
            <c:param name="type" value="addclasscourse"/>
            <c:param name="id" value="${item.id}"/>
          </c:url><c:url var="editUrl" value="/admin-class-course-page">
          <c:param name="type" value="edit"/>
          <c:param name="id" value="${item.id}"/>
        </c:url>
          <c:url var="deleteUrl" value="/admin-course-page">
            <c:param name="type" value="delete"/>
            <c:param name="id" value="${item.id}"/>
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
                <c:if test="${item.dayOnWeek != null and  item.dayOnWeek != 0}">
                    <span class="badge bg-label-${bg} me-1">
       <c:if test="${item.dayOnWeek != null and  item.dayOnWeek eq 1}">
           Chủ nhật
       </c:if>
       <c:if test="${item.dayOnWeek != null and item.dayOnWeek != 1}">
           Thứ ${item.dayOnWeek}
       </c:if>
    </span>
                </c:if>
            </td>
<td>${item.roomModel.areaCode}${item.roomModel.roomCode}</td>
            <td>
                ${item.startLesson != null ? Integer.toString(item.startLesson) : ""}-${item.endLesson != null ? Integer.toString(item.endLesson-1) : ""}

            </td> <td>
              ${item.startWeek != null ? Integer.toString(item.startWeek) : ""}-${item.endWeek != null ? Integer.toString(item.endWeek) : ""}
          </td>
            <td>
              <span class="badge bg-label-${bg} me-1">${item.slotRoom}</span>
            </td>
            <td>
            ${item.teacherModel.name}
            </td>
            <td>
              <div class="dropdown">
                <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                  <i class="bx bx-dots-vertical-rounded"></i>
                </button>
                <div class="dropdown-menu">
                  <a class="dropdown-item" href="${editUrl}"><i class="bx bx-edit-alt me-1"></i> Edit</a>
                  <a class="dropdown-item" href="${deleteUrl}"><i class="bx bx-trash me-1"></i> Delete</a>
                </div>
              </div>
            </td>
          </tr>
        </c:forEach>
        </tbody>

      </table>
      <%--            <div class="demo-inline-spacing">--%>
      <%--                <nav aria-label="Page navigation">--%>
      <%--                    <ul class="pagination justify-content-end">--%>
      <%--                        <li class="page-item prev">--%>
      <%--                            <a class="page-link" href="javascript:void(0);"><i class="tf-icon bx bx-chevrons-left"></i></a>--%>
      <%--                        </li>--%>
      <%--                        <li class="page-item">--%>
      <%--                            <a class="page-link" href="javascript:void(0);">1</a>--%>
      <%--                        </li>--%>
      <%--                        <li class="page-item">--%>
      <%--                            <a class="page-link" href="javascript:void(0);">2</a>--%>
      <%--                        </li>--%>
      <%--                        <li class="page-item active">--%>
      <%--                            <a class="page-link" href="javascript:void(0);">3</a>--%>
      <%--                        </li>--%>
      <%--                        <li class="page-item">--%>
      <%--                            <a class="page-link" href="javascript:void(0);">4</a>--%>
      <%--                        </li>--%>
      <%--                        <li class="page-item">--%>
      <%--                            <a class="page-link" href="javascript:void(0);">5</a>--%>
      <%--                        </li>--%>
      <%--                        <li class="page-item next">--%>
      <%--                            <a class="page-link" href="javascript:void(0);"><i class="tf-icon bx bx-chevrons-right"></i></a>--%>
      <%--                        </li>--%>
      <%--                    </ul>--%>
      <%--                </nav>--%>
      <%--            </div>--%>

    </div>
  </div>
</div>
<%--<script>--%>
<%--  $('#selectAcademic').change(function () {--%>
<%--    // Lấy giá trị đã chọn--%>
<%--    var selectedValue = $(this).val();--%>

<%--    // Xử lý logic sau khi giá trị thay đổi--%>
<%--    // console.log('Đã chọn giá trị: ' + selectedValue);--%>
<%--    let data={--%>
<%--      facultyCode: $(this).val()--%>
<%--    }--%>
<%--    $.ajax({--%>
<%--      url:'${APIurl}',--%>
<%--      type:'GET',--%>
<%--      contentType: 'application/json',//client send type data to server--%>
<%--      data:data,--%>
<%--      dataType:'json',//server send type data to client--%>
<%--      success: function (result) {--%>
<%--        $('#tableBody').empty();--%>
<%--        console.log(result);--%>
<%--        for (var i = 0; i < result.list.length; i++) {--%>
<%--          addRowToTable(result.list[i],i);--%>
<%--        }--%>
<%--        // Thêm một hàng mới vào bảng--%>
<%--      },--%>
<%--      error:function(error){--%>
<%--        $("#selectTypeOpt").val("bg-danger");--%>
<%--        $("#selectPlacement").val("top-0 start-50 translate-middle-x");--%>
<%--        $("#contentToast").text("Failed");--%>
<%--        $("#showToastPlacement").click();--%>
<%--      }--%>
<%--    })--%>
<%--    // Thêm các xử lý khác tùy theo yêu cầu của bạn--%>
<%--  });--%>
<%--  $("#btnAdd").on("click",function (e){--%>
<%--    e.preventDefault();--%>
<%--    let BT = parseFloat($("#BT").val()) || 0; // Chuyển đổi giá trị sang số, mặc định là 0 nếu không hợp lệ--%>
<%--    let BV = parseFloat($("#BV").val()) || 0;--%>
<%--    let CK = parseFloat($("#CK").val()) || 0;--%>
<%--    let DA = parseFloat($("#DA").val()) || 0;--%>
<%--    let GK = parseFloat($("#GK").val()) || 0;--%>
<%--    let QT = parseFloat($("#QT").val()) || 0;--%>
<%--    let TH = parseFloat($("#TH").val()) || 0;--%>
<%--    let total = BT + BV + CK + DA + GK + QT + TH;--%>
<%--    let data={--%>
<%--      code:$("#codeCourse").val().trim(),--%>
<%--      name:$("#nameCourse").val().trim(),--%>
<%--      facultyCode:$("#codeFaculty").val(),--%>
<%--      numberCredit:parseFloat($("#numberCredit").val())||0,--%>
<%--      pointStudy:parseFloat($("#pointStudy").val())||0,--%>
<%--      pointBT:BT,--%>
<%--      pointBV:BV,--%>
<%--      pointCK:CK,--%>
<%--      pointDA:DA,--%>
<%--      pointGK:GK,--%>
<%--      pointQT:QT,--%>
<%--      pointTH:TH--%>
<%--    }--%>
<%--    console.log(data);--%>
<%--    let message = [];--%>
<%--    let isEmpty = false;  // Biến để kiểm tra xem có thuộc tính rỗng không--%>

<%--    for (let key in data) {--%>
<%--      if (data.hasOwnProperty(key)) {--%>
<%--        if (!data[key]&&Number(data[key]) !== 0) {--%>
<%--          isEmpty = true;--%>
<%--          message.push(key);--%>
<%--        }--%>
<%--      }--%>
<%--    }--%>

<%--    if (isEmpty) {--%>
<%--      let errorMessage = message.join(', ');  // Chuyển mảng thành chuỗi, sử dụng dấu phẩy làm phân cách--%>
<%--      $("#selectTypeOpt").val("bg-warning");--%>
<%--      $("#selectPlacement").val("top-0 start-50 translate-middle-x");--%>
<%--      $("#contentToast").text("Thiếu trường");--%>
<%--      $("#bodyToast").text("Thiếu trường "+errorMessage);--%>
<%--      $("#showToastPlacement").click();--%>
<%--    } else {--%>
<%--      if(total===1){--%>
<%--        addClass(data);--%>
<%--      }else{--%>
<%--        $("#selectTypeOpt").val("bg-warning");--%>
<%--        $("#selectPlacement").val("top-0 start-50 translate-middle-x");--%>
<%--        $("#contentToast").text("Tổng hệ số phải bằng 1");--%>
<%--        $("#bodyToast").text("Tổng hệ số bằng "+total);--%>
<%--        $("#showToastPlacement").click();--%>
<%--      }--%>
<%--    }--%>
<%--  })--%>
<%--  function addClass(data){--%>
<%--    $.ajax({--%>
<%--      url:'${APIurl}',--%>
<%--      type:'POST',--%>
<%--      contentType: 'application/json',//client send type data to server--%>
<%--      data:JSON.stringify(data),--%>
<%--      dataType:'json',//server send type data to client--%>
<%--      success: function (result) {--%>
<%--        var modal = $('#addFaculyModal');--%>
<%--        modal.modal('hide');--%>
<%--        $('#tableBody').empty();--%>
<%--        for (var i = 0; i < result.length; i++) {--%>
<%--          addRowToTable(result[i],i);--%>
<%--        }--%>
<%--        // Thêm một hàng mới vào bảng--%>

<%--      },--%>
<%--      error:function(error){--%>
<%--        console.log(error);--%>

<%--      }--%>
<%--    })--%>
<%--  }--%>
<%--  function addRowToTable(item,index) {--%>
<%--    switch (index % 6) {--%>
<%--      case 0:--%>
<%--        var bg = "primary";--%>
<%--        break;--%>
<%--      case 1:--%>
<%--        var bg = "success";--%>
<%--        break;--%>
<%--      case 2:--%>
<%--        var bg = "danger";--%>
<%--        break;--%>
<%--      case 3:--%>
<%--        var bg = "warning";--%>
<%--        break;--%>
<%--      case 4:--%>
<%--        var bg = "info";--%>
<%--        break;--%>
<%--      case 5:--%>
<%--        var bg = "dark";--%>
<%--        break;--%>
<%--      default:--%>
<%--        var bg="primary";--%>
<%--        break;--%>
<%--    }--%>
<%--    var row = '<tr>' +--%>
<%--            '<td> <strong>' + (index+1) + '</strong></td>' +--%>
<%--            '<td><span class="badge bg-label-'+bg+' me-1">' + item.code + '</span></td>' +--%>
<%--            '<td>'+item.name+'</td>'+--%>
<%--            '<td><span class="badge bg-label-'+bg+' me-1">' + item.numberCredit + '</span></td>' +--%>
<%--            '<td>' +--%>
<%--            '<div class="dropdown">' +--%>
<%--            '<button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">' +--%>
<%--            '<i class="bx bx-dots-vertical-rounded"></i>' +--%>
<%--            '</button>' +--%>
<%--            '<div class="dropdown-menu">' +--%>
<%--            '<a class="dropdown-item" href="/DUT/admin-course-page?type=edit&id='+item.id+'"><i class="bx bx-edit-alt me-1"></i> Edit</a>' +--%>
<%--            '<a class="dropdown-item"  href="/DUT/admin-course-page?type=delete&id='+item.id+'"><i class="bx bx-trash me-1"></i> Delete</a>' +--%>
<%--            '</div>' +--%>
<%--            '</div>' +--%>
<%--            '</td>' +--%>
<%--            '</tr>';--%>

<%--    // Append the row to the table body--%>
<%--    $('#tableBody').append(row);--%>
<%--  }--%>
<%--</script>--%>
</body>
</html>
