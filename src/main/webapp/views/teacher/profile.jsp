<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 06-Dec-23
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-teacher-profile"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Tài khoản /</span> ${model.name}</h4>

    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-pills flex-column flex-md-row mb-3">
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value='/teacher-home-page?code=${model.code}'/>"><i class="bx bxs-user-account me-1"></i>Thông tin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bx bx-bell me-1"></i> Kết quả học tập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/teacher-home-page?type=changepassword&id=${model.id}'/>"><i class="bx bx-user me-1"></i> Đổi mật khẩu</a>
                </li>
            </ul>
            <div class="card mb-4">
                <h5 class="card-header">Thông tin giáo viên</h5>
                <div class="bs-toast toast toast-placement-ex m-2 fade hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
                    <div class="toast-header">
                        <i class="bx bx-bell me-2"></i>
                        <div class="me-auto fw-semibold" id="contentToast"></div>
                        <%--            <small>11 mins ago</small>--%>
                        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                    <div class="toast-body" id="bodyToast"></div>
                </div>

                <!-- Account -->
                <div class="card-body">
                    <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img src="<c:url value='${model.image!=null?model.image:"/template/admin/assets/img/avatars/avatar.jpg"}'/>" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                        <div class="button-wrapper">
                            <%--                            <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">--%>
                            <%--                                <span class="d-none d-sm-block">Upload Image</span>--%>
                            <%--                                <i class="bx bx-upload d-block d-sm-none"></i>--%>
                            <%--                                <input type="file" id="upload" class="account-file-input" hidden="" accept="image/png, image/jpeg">--%>
                            <%--                            </label>--%>
                            <%--                            <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">--%>
                            <%--                                <i class="bx bx-reset d-block d-sm-none"></i>--%>
                            <%--                                <span class="d-none d-sm-block">Reset</span>--%>
                            <%--                            </button>--%>
                            <%--                            <p class="text-muted mb-0">Ảnh đại diện của sinh viên</p>--%>
                        </div>
                    </div>
                </div>
                <hr class="my-0">
                <div class="card-body">
                    <form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                            <input class="form-control" type="hidden" id="idTeacher" name="firstName" value="${model.id}" autofocus="">
                            <div class="mb-3 col-md-4">
                                <label for="codeTeacher" class="form-label">Mã Giáo viên</label>
                                <input readonly  class="form-control" type="text" id="codeTeacher" name="firstName" value="${model.code}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="name" class="form-label">Họ và tên</label>
                                <input readonly class="form-control" type="text" id="name" name="name" value="${model.name}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="cccdCode" class="form-label">Căn cước công dân</label>
                                <input readonly class="form-control" type="text" id="cccdCode" name="name" value="${model.cccd}" autofocus="">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="sex" class="form-label">Giới tính</label>
                                <input readonly  class="form-control" type="text" id="sex" name="sex" value="${model.sex}">

                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="birthday" class="form-label">Ngày sinh</label>
                                <input readonly class="form-control" type="date" id="birthday" name="birthday" value="${model.birthday}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="hometown" class="form-label">Quê quán</label>
                                <input readonly class="form-control" type="text" id="hometown" name="birthday" value="${model.hometown}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="address" class="form-label">Địa chỉ</label>
                                <input  class="form-control" type="text" id="address" name="birthday" value="${model.address}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="district" class="form-label">Quận</label>
                                <input  class="form-control" type="text" id="district" name="district" value="${model.district}">
                            </div>
                            <div class="mb-3 col-md-4">
                                <label for="city" class="form-label">Thành Phố</label>
                                <input  class="form-control" type="hidden" id="cityOld" name="district" value="${model.city}">
                                <select id="city" class="form-select">
                                </select>
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="phone" class="form-label">Số điện thoại</label>
                                <div class="input-group input-group-merge">
                                    <span class="input-group-text">VN (+84)</span>
                                    <input  type="text" id="phone" name="phoneNumber" class="form-control" value="${model.phone}">
                                </div>
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="email" class="form-label">Email</label>
                                <input  class="form-control" type="email" id="email" name="birthday" value="${model.email}">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="level" class="form-label">Trình độ</label>
                                <input readonly class="form-control" type="email" id="level" name="level" value="${model.level}">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="codeFaculty" class="form-label">Khoa</label>
                                <input readonly class="form-control" type="text" id="codeFaculty" name="level" value="${model.facultyModel.name}">
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="specialized" class="form-label">Chuyên ngành</label>
                                <input readonly class="form-control" type="text" id="specialized" name="level" value="${model.specialized}">
                            </div>
                        </div>
                        <div class="mt-2">
                            <button type="submit" id="btnUpdate" class="btn btn-primary me-2">Save changes</button>
                            <button onclick="window.location.href='<c:url value="/admin-teacher-page"/>'" type="reset" class="btn btn-outline-secondary">Back</button>
                            <input type="hidden" id="showToastPlacement" class="btn btn-primary d-block"></input>
                            <input type="hidden"  id="selectTypeOpt" value="bg-success">
                            <input type="hidden"  id="selectPlacement" value="top-0 start-50 translate-middle-x">
                        </div>
                    </form>
                </div>
                <!-- /Account -->
            </div>
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
        var city=$("#cityOld").val();
        // Đổ dữ liệu vào dropdown menu
        var select = $('#city');
        $.each(provinces, function(index, province) {
            var row='<option value="' + (index+1) + '">' + province + '</option>';
            if(city!==''&&province===city){
                row='<option selected value="' + (index+1) + '">' + province + '</option>';
            }
            select.append(row);
        });
    });
</script>

<script>
    var cityOption;

    $("#city").change(function() {
        cityOption = $(this).find("option:selected").text();
        if(cityOption==="-- Tỉnh,thành phố --")cityOption="";
        // Sử dụng selectedOptionText trong AJAX request hoặc xử lý dữ liệu khác
    });
    $("#btnUpdate").on("click",function (e){
        e.preventDefault();
        var data={
            id:$("#idTeacher").val(),
            code:$("#codeTeacher").val(),
            name:$("#name").val(),
            birthday:$("#birthday").val(),
            sex:$("#sex").val(),
            facultyCode:$("#codeFaculty").val(),
            address:$("#address").val(),
            district:$("#district").val(),
            city:cityOption,
            level:$("#level").val(),
            hometown:$("#hometown").val(),
            specialized:$("#specialized").val(),
            phone:$("#phone").val(),
            email: $("#email").val(),
            cccd:$("#cccdCode").val()
        }
        // var message = [];
        // var isEmpty = false;  // Biến để kiểm tra xem có thuộc tính rỗng không
        //
        // for (var key in data) {
        //     if (data.hasOwnProperty(key)) {
        //         if (!data[key]) {
        //             isEmpty = true;
        //             message.push(key);
        //         }
        //     }
        // }
        //
        // if (isEmpty) {
        //     var errorMessage = message.join(', ');  // Chuyển mảng thành chuỗi, sử dụng dấu phẩy làm phân cách
        //     $("#selectTypeOpt").val("bg-warning");
        //     $("#selectPlacement").val("top-0 start-50 translate-middle-x");
        //     $("#contentToast").text("Thiếu trường");
        //     $("#bodyToast").text("Thiếu trường "+errorMessage);
        //     $("#showToastPlacement").click();
        // } else {
        //     updateProfile(data);
        // }
        updateProfile(data);
        console.log(data);
    })
    function updateProfile(data){
        $.ajax({
            url:'${APIurl}',
            type:'PUT',
            contentType: 'application/json',//client send type data to server
            data:JSON.stringify(data),
            dataType:'json',//server send type data to client
            success: function (result) {
                $("#selectTypeOpt").val("bg-success");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cập nhật thông tin thành công");
                $("#showToastPlacement").click();
            },
            error:function(error){
                console.log(error);
                $("#selectTypeOpt").val("bg-danger");
                $("#selectPlacement").val("top-0 start-50 translate-middle-x");
                $("#contentToast").text("Cập nhật thông tin thất bại");
                $("#showToastPlacement").click();
            }
        })
    }
</script>

</body>
</html>
