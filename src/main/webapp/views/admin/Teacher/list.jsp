<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 02-Dec-23
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-teacher"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Khoa /</span> Giáo viên</h4>
    <div class="card">
        <h5 class="card-header">Danh sách các giáo viên</h5>
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
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addFaculyModal">
                            Thêm giáo viên
                        </button>
                        <div class="input-group mt-4">
                            <select class="form-select" id="inputGroupSelect04" aria-label="Example select with button addon">
                                <option value="" selected="">-- Khoa --</option>
                                <c:forEach var="item" items="${faculties}">
                                    <option value="${item.code}">${item.name}</option>
                                </c:forEach>
                            </select>
                            <button class="btn btn-outline-primary" type="button">Filter</button>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="addFaculyModal" tabindex="-1" style="display: none;" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel1">Thêm giáo viên</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col mb-3">
                                                <label for="msgv" class="form-label">Mã số giáo viên</label>
                                                <input name="mssv" type="text" id="msgv" class="form-control" placeholder="Nhập mã số giáo viên" required>
                                            </div>
                                        </div>
                                        <div class="row g-2">
                                            <div class="col mb-3">
                                                <label for="name" class="form-label">Tên</label>
                                                <input name="name" type="text" id="name" class="form-control" placeholder="Nhập tên giáo viên" required>
                                            </div>
                                            <div class="col mb-3">
                                                <label for="cccdCode" class="form-label">CCCD</label>
                                                <input name="name" type="text" id="cccdCode" class="form-control" placeholder="Nhập căn cước công dân" pattern="\d{12}" title="Cần nhập 12 chữ số" required>
                                            </div>
                                        </div>
                                        <div class="row g-3">
                                            <div class="col mb-3">
                                                <label for="hometown" class="form-label">Quê quán</label>
                                                <select id="hometown" class="form-select">
                                                    <option value="" selected>-- Tỉnh,thành phố --</option>
                                                </select>
                                            </div>
                                            <div class="col mb-3">
                                                <label for="birthday" class="form-label">Sinh nhật</label>
                                                <input type="date" id="birthday" class="form-control" placeholder="DD / MM / YY" required>
                                            </div>
                                            <div class="col mb-3">
                                                <label for="sex" class="form-label">Giới tính</label>
                                                <select id="sex" class="form-select">
                                                    <option value="" selected>-- Giới tính --</option>
                                                    <option value="1">Nam</option>
                                                    <option value="2">Nữ</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row g-2">
                                            <div class="col mb-3">
                                                <label for="level" class="form-label">Trình độ</label>
                                                <select id="level" class="form-select">
                                                    <option value="" selected>-- Trình độ --</option>
                                                    <option value="1">Cử nhân</option>
                                                    <option value="2">Thạc sĩ</option>
                                                    <option value="3">Tiến sĩ</option>
                                                    <option value="4">Phó giáo sư</option>
                                                    <option value="5">Giáo sư</option>
                                                </select>
                                            </div>
                                            <div class="col mb-3">
                                                <label for="specialized" class="form-label">Chuyên ngành</label>
                                                <input type="text" id="specialized" class="form-control" placeholder="Nhập chuyên ngành" required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col mb-3">
                                                <label for="codeFaculty" class="form-label">Khoa</label>
                                                <select id="codeFaculty" class="form-select" required>
                                                    <option value="" selected>-- Khoa --</option>
                                                    <c:forEach var="item" items="${faculties}">
                                                        <option value="${item.code}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <%--                    <div class="row">--%>
                                        <%--                      <div class="col mb-3">--%>
                                        <%--                        <label for="image" class="form-label">Ảnh</label>--%>
                                        <%--                        <input name="email" type="text" id="image" class="form-control" placeholder="Link Avatar" required>--%>
                                        <%--                      </div>--%>
                                        <%--                    </div>--%>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                            Đóng
                                        </button>
                                        <button id="btnAdd" type="submit" class="btn btn-primary">Thêm</button>
                                        <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
                                        <input type="hidden"  id="selectTypeOpt" value="bg-success">
                                        <input type="hidden"  id="selectPlacement" value="top-0 start-50 translate-middle-x">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="table-responsive text-nowrap">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>MSGV</th>
                    <th>Tên</th>
                    <th>Giới tính</th>
                    <th>Khoa</th>
                    <th>Quê quán</th>
                    <th>Xem</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody class="table-border-bottom-0" id="tableBody">
                <c:forEach var="item" items="${model.list}" varStatus="loop">
                    <c:url var="editUrl" value="/admin-student-page">
                        <c:param name="type" value="edit"/>
                        <c:param name="id" value="${item.id}"/>
                    </c:url>
                    <c:url var="deleteUrl" value="/admin-student-page">
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
                                ${item.sex}
                        </td>
                        <td> <span class="badge bg-label-${bg} me-1">${item.facultyModel.name}</span></td>
                        <td>${item.hometown}</td>
                        <td><button onclick="window.location.href='<c:url value="/admin-teacher-page?type=edit&id=${item.id}"/>'" type="button" class="btn rounded-pill btn-primary">Xem</button></td>
                        <td><button type="button" class="btn rounded-pill btn-danger">Xóa</button></td>
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
    $(document).ready(function() {
        // Danh sách tỉnh thành trong mảng JavaScript
        var provinces = [
            "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định",
            "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông",
            "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương",
            "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng",
            "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên",
            "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình",
            "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Thành phố Hồ Chí Minh", "Trà Vinh", "Tuyên Quang",
            "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
        ];

        // Đổ dữ liệu vào dropdown menu
        var select = $('#hometown');
        $.each(provinces, function(index, province) {
            var row='<option value="' + (index+1) + '">' + province + '</option>';
            select.append(row);
        });
    });
</script>
<script>
    var levelOption;
    var hometownOption;
    $("#level").change(function() {
         levelOption = $(this).find("option:selected").text();
        if(levelOption==="-- Trình độ --")levelOption="";
        // Sử dụng selectedOptionText trong AJAX request hoặc xử lý dữ liệu khác
    });
    $("#hometown").change(function() {
        hometownOption = $(this).find("option:selected").text();
        if(hometownOption==="-- Tỉnh,thành phố --")hometownOption="";
        // Sử dụng selectedOptionText trong AJAX request hoặc xử lý dữ liệu khác
    });
    $("#btnAdd").on("click",function (e){
        e.preventDefault();
        var data={
            code:$("#msgv").val(),
            name:$("#name").val(),
            birthday:$("#birthday").val(),
            sex:$("#sex").val(),
            facultyCode:$("#codeFaculty").val(),
            level:levelOption,
            hometown:hometownOption,
            specialized:$("#specialized").val(),
            cccd:$("#cccdCode").val()
        }
        console.log()
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
            addTeacher(data);
        }
    })
    function addTeacher(data){
        $.ajax({
            url:'${APIurl}',
            type:'POST',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                var modal = $('#addFaculyModal');
                modal.modal('hide');
                $('#tableBody').empty();
                for (var i = 0; i < result.length; i++) {
                    addRowToTable(result[i],i);
                }
                // Thêm một hàng mới vào bảng

            },
            error:function(error){
                console.log(error);

            }
        })
    }

    function addRowToTable(item, index) {
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
                var bg = "primary";
                break;
        }

        var linkEdit = "/DUT/admin-teacher-page?type=edit&id=" + item.id;
        var sex;
        var row = '<tr>' +
            '<td><strong>' + (index + 1) + '</strong></td>' +
            '<td><span class="badge bg-label-' + bg + ' me-1">' + item.code + '</span></td>' +
            '<td>' + item.name + '</td>' +
            '<td>' + item.sex + '</td>' +
            '<td><span class="badge bg-label-' + bg + ' me-1">' + item.facultyModel.name + '</span></td>' +
            '<td>' + (item.hometown != null ? item.hometown : "") + '</td>' +
            '<td><button onclick="window.location.href=\'' + linkEdit + '\'" type="button" class="btn rounded-pill btn-primary">Xem</button></td>' +
            '<td><button type="button" class="btn rounded-pill btn-danger">Xóa</button></td>' +
            '</tr>';
        $('#tableBody').append(row);
    }

</script>
</body>
</html>
