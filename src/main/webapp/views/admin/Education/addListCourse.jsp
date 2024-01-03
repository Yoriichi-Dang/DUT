<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 08-Dec-23
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIEducation" value="/api-admin-education-course"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Chương trình đào tạo /</span> Thêm học phần</h4>
    <div class="card">
        <h5 class="card-header">${model.name}</h5>
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
                <div class="col-lg-4 col-md-6">
                    <div class="mt-0">
                        <button id="btnAdd" type="button" class="btn btn-primary" >
                            Lưu
                        </button>
                        <div class="mt-3">
                            <label for="semester" class="col-md-2 col-form-label">Học kỳ</label>
                            <div class="col-md-4">
                                <input class="form-control" id="semester" type="number" value="1" required min="1" max="${model.semesterStudy}">
                            </div>
                        </div>
                        <!-- Button trigger modal -->
                        <div class="input-group mt-3">
                            <select class="form-select" id="selectAcademic" aria-label="Example select with button addon">
                                <option value="" selected="">-- Tất cả khoa --</option>
                                <c:forEach var="item" items="${faculties}" varStatus="loop">
                                    <option value="${item.code}">${item.name}</option>
                                </c:forEach>
                            </select>
                            <%--                            <button class="btn btn-outline-primary" type="button">Filter</button>--%>
                        </div>
                        <!-- Modal -->
                        <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block">
                        <input type="hidden"  id="selectTypeOpt" value="bg-success">
                        <input type="hidden"  id="selectPlacement" value="top-0 start-50 translate-middle-x">
                    </div>
                </div>
            </div>
        </div>

        <div class="table-responsive text-nowrap">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Mã học phần</th>
                    <th>Tên học phần</th>
                    <th>Tín chỉ</th>
                    <th>
                        <input class="form-check-input select-all" type="checkbox"  id="selectAll">
                    </th>
                </tr>
                </thead>
                <input name="id" type="hidden" id="codeEducation" class="form-control" value="${model.code}" required>
                <tbody class="table-border-bottom-0" id="tableBody">
                <c:forEach var="item" items="${model.courseModelList}" varStatus="loop">
                    <c:url var="editUrl" value="/admin-class-page">
                        <c:param name="type" value="edit"/>
                        <c:param name="id" value="${item.id}"/>
                    </c:url>
                    <c:url var="deleteUrl" value="/admin-class-page">
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
                                ${item.name}
                        </td>
                        <td>
                            <span class="badge bg-label-${bg} me-1">${item.numberCredit}</span>
                        </td>
                        <td>
                            <div class="form-check">
                                 <input class="form-check-input other-checkboxes" type="checkbox" value="${item.code}" id="defaultCheck">
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
<script>
    var selectAllCheckbox = document.querySelector('.select-all');
    var otherCheckboxes = document.querySelectorAll('.other-checkboxes');
    var countCheckbox = otherCheckboxes.length;
    console.log(countCheckbox);
    var countIsCheckbox = 0;
    otherCheckboxes.forEach(function(checkbox) {
        checkbox.addEventListener("change", function() {
            if (checkbox.checked) {
                countIsCheckbox++;
            } else {
                countIsCheckbox--;
            }
            if (countIsCheckbox === countCheckbox) {
                selectAllCheckbox.checked = true;
            } else {
                selectAllCheckbox.checked = false;
            }
        });
    });
    selectAllCheckbox.addEventListener("change", function() {
        var isChecked = selectAllCheckbox.checked;
        if (selectAllCheckbox.checked)
            countIsCheckbox = countCheckbox;
        else countIsCheckbox = 0;
        otherCheckboxes.forEach(function(checkbox) {
            checkbox.checked = isChecked;
        });
    });
    $('#selectAcademic').change(function () {
        // Lấy giá trị đã chọn
        var selectedValue = $(this).val();

        // Xử lý logic sau khi giá trị thay đổi
        console.log('Đã chọn giá trị: ' + selectedValue);
        let data={
            code:$("#codeEducation").val(),
            facultyCode: $(this).val()
        }
        $.ajax({
            url:'${APIEducation}',
            type:'GET',
            contentType: 'application/json',//client send type data to server
            data:data,
            dataType:'json',//server send type data to client
            success: function (result) {
                $('#tableBody').empty();
                console.log(result);
                for (var i = 0; i < result.list.length; i++) {
                    addRowToTable(result.list[i],i);
                }
                selectAllCheckbox.checked=false;
                otherCheckboxes = document.querySelectorAll('.other-checkboxes');
                countCheckbox = otherCheckboxes.length;
                // Thêm một hàng mới vào bảng
            },
            error:function(error){
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Failed");
                $("#showToastPlacement").click();
            }
        })
        // Thêm các xử lý khác tùy theo yêu cầu của bạn
    });
    $("#btnAdd").on("click",function (e){
        e.preventDefault();
        let selectedValues = $('.other-checkboxes:checked').map(function() {
            return this.value;
        }).get();
        console.log(selectedValues);
        let data={
            code:$("#codeEducation").val(),
            semesterStudy:$("#semester").val(),
            listCourseCode:selectedValues
        }
        addListCourse(data);
    })
    function addListCourse(data){
        $.ajax({
            url:'${APIEducation}',
            type:'POST',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                $("#selectTypeOpt").val("bg-success");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Thêm thành công");
                $("#bodyToast").text("Thêm học phần thành công");
                $("#showToastPlacement").click();
                $('#tableBody').empty();
                console.log(result);
                for (var i = 0; i < result.list.length; i++) {
                    addRowToTable(result.list[i],i);
                }
                selectAllCheckbox.checked=false;
                otherCheckboxes = document.querySelectorAll('.other-checkboxes');
                countCheckbox = otherCheckboxes.length;
                // Thêm một hàng mới vào bảng

            },
            error:function(error){
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Thêm thất bại");
                $("#bodyToast").text("Thêm học phần thất bại");
                $("#showToastPlacement").click();

            }
        })
    }
    function addRowToTable(item,index) {
        switch (index % 6) {
            case 0:
                var bg = "primary";
                break;
            case 1:
                var bg = "success";
                break;
            case 2:
                var bg = "danger";
                break;
            case 3:
                var bg = "warning";
                break;
            case 4:
                var bg = "info";
                break;
            case 5:
                var bg = "dark";
                break;
            default:
                var bg="primary";
                break;
        }
        var row = '<tr>' +
            '<td> <strong>' + (index+1) + '</strong></td>' +
            '<td><span class="badge bg-label-'+bg+' me-1">' + item.code + '</span></td>' +
            '<td>'+item.name+'</td>'+
            '<td><span class="badge bg-label-'+bg+' me-1">' + item.numberCredit.toFixed(1) + '</span></td>' +
            '<td>' +
            '<div class="form-check">' +
            '   <input class="form-check-input other-checkboxes" type="checkbox" value="'+item.code+'" id="defaultCheck">' +
            '</div>' +
            '</td>'+
            '</tr>';

        // Append the row to the table body
        $('#tableBody').append(row);
    }


</script>
</body>
</html>
