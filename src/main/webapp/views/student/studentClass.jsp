<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 07-Dec-23
  Time: 1:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
  <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Lớp /</span> ${model.classCode}</h4>
  <div class="card">
    <h5 class="card-header">Danh sách các sinh viên</h5>
    <div class="table-responsive text-nowrap">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>STT</th>
          <th>MSSV</th>
          <th>Tên</th>
          <th>Email</th>
          <th>Số điện thoại</th>
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
                ${item.email!=""?item.email:""}
            </td>
            <td>
                ${item.phone!=""?item.phone:""}
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

</body>
</html>
