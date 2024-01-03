<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 09-Dec-23
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIEducation" value="/api-admin-education"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Chương trình đào tạo /</span> Học phần</h4>
    <div class="card">
        <h5 class="card-header">${model.name}</h5>
        <div class="card-body">
            <div class="row gy-3">
                <!-- Default Modal -->
                <div class="col-lg-4 col-md-6">
                    <div class="mt-0">
                        <!-- Button trigger modal -->
                        <div class="input-group mt-1">
                            <select class="form-select" id="selectAcademic" aria-label="Example select with button addon">
                                <option value="" selected="">-- Tất cả khoa --</option>
                                <c:forEach var="item" items="${faculties}" varStatus="loop">
                                    <option value="${item.code}">${item.name}</option>
                                </c:forEach>
                            </select>
                            <%--                            <button class="btn btn-outline-primary" type="button">Filter</button>--%>
                        </div>
                        <!-- Modal -->

                    </div>
                </div>
            </div>
        </div>

        <div class="table-responsive text-nowrap">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>STT</th>
<%--                    <th>Học kỳ</th>--%>
                    <th>Mã học phần</th>
                    <th>Tên học phần</th>
                    <th>Tín chỉ</th>
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
<%--                        <td><strong>${item.semester}</strong></td>--%>
                        <td>
                            <span class="badge bg-label-${bg} me-1">${item.code}</span>
                        </td>
                        <td>
                                ${item.name}
                        </td>
                        <td>
                            <span class="badge bg-label-${bg} me-1">${item.numberCredit}</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>


    </div>
</div>
<script>
    $('#selectAcademic').change(function () {
        // Lấy giá trị đã chọn
        var selectedValue = $(this).val();

        // Xử lý logic sau khi giá trị thay đổi
        console.log('Đã chọn giá trị: ' + selectedValue);
        let data={
            code:$("#codeEducation").val(),
            facultyCode: $(this).val(),
            type:"educationcourse"
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
                for (var i = 0; i < result.courseModelList.length; i++) {
                    addRowToTable(result.courseModelList[i],i);
                }
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
            '</tr>';

        // Append the row to the table body
        $('#tableBody').append(row);
    }

</script>
</body>
</html>
