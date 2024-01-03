<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 06-Dec-23
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y">
    <div class="row">
        <!-- <div class="col-md mb-4 mb-md-0">
<small class="text-light fw-semibold">Basic Accordion</small>
<div class="accordion mt-3" id="accordionExample">
<div class="card accordion-item">
  <h2 class="accordion-header" id="headingOne">
    <button type="button" class="accordion-button collapsed" data-bs-toggle="collapse" data-bs-target="#accordionOne" aria-expanded="false" aria-controls="accordionOne">
      Accordion Item 1
    </button>
  </h2>

  <div id="accordionOne" class="accordion-collapse collapse" data-bs-parent="#accordionExample" style="">
    <div class="accordion-body">
      Lemon drops chocolate cake gummies carrot cake chupa chups muffin topping. Sesame snaps icing
      marzipan gummi bears macaroon dragée danish caramels powder. Bear claw dragée pastry topping
      soufflé. Wafer gummi bears marshmallow pastry pie.
    </div>
  </div>
</div>
<div class="card accordion-item">
  <h2 class="accordion-header" id="headingTwo">
    <button type="button" class="accordion-button collapsed" data-bs-toggle="collapse" data-bs-target="#accordionTwo" aria-expanded="false" aria-controls="accordionTwo">
      Accordion Item 2
    </button>
  </h2>
  <div id="accordionTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
    <div class="accordion-body">
      Dessert ice cream donut oat cake jelly-o pie sugar plum cheesecake. Bear claw dragée oat cake
      dragée ice cream halvah tootsie roll. Danish cake oat cake pie macaroon tart donut gummies.
      Jelly beans candy canes carrot cake. Fruitcake chocolate chupa chups.
    </div>
  </div>
</div>
<div class="card accordion-item">
  <h2 class="accordion-header" id="headingThree">
    <button type="button" class="accordion-button collapsed" data-bs-toggle="collapse" data-bs-target="#accordionThree" aria-expanded="false" aria-controls="accordionThree">
      Accordion Item 3
    </button>
  </h2>
  <div id="accordionThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
    <div class="accordion-body">
      Oat cake toffee chocolate bar jujubes. Marshmallow brownie lemon drops cheesecake. Bonbon
      gingerbread marshmallow sweet jelly beans muffin. Sweet roll bear claw candy canes oat cake
      dragée caramels. Ice cream wafer danish cookie caramels muffin.
    </div>
  </div>
</div>
</div>
</div> -->
        <div class="col-xl-12">
            <h6 class="text-muted">Thông báo</h6>
            <div class="nav-align-top mb-4">
                <ul class="nav nav-pills mb-3 nav-fill col-12 col-sm-4" role="tablist">
                    <li class="nav-item">
                        <button type="button" class="nav-link active" role="tab" data-bs-toggle="tab" data-bs-target="#navs-pills-justified-home" aria-controls="navs-pills-justified-home" aria-selected="true">
                            <i class="tf-icons bx bx-home"></i> Thông báo chung
                            <span class="badge rounded-pill badge-center h-px-20 w-px-20 bg-danger">3</span>
                        </button>
                    </li>

                    <li class="nav-item">
                        <button type="button" class="nav-link" role="tab" data-bs-toggle="tab" data-bs-target="#navs-pills-justified-messages" aria-controls="navs-pills-justified-messages" aria-selected="false">
                            <i class="tf-icons bx bx-message-square"></i> Thông báo học phần
                        </button>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade active show" id="navs-pills-justified-home" role="tabpanel">
                        <div class="accordion mt-3" id="accordionExample">
<%--                            <div class="card accordion-item active">--%>
<%--                                <h2 class="accordion-header" id="headingOne">--%>
<%--                                    <button type="button" class="accordion-button" data-bs-toggle="collapse" data-bs-target="#accordionOne" aria-expanded="true" aria-controls="accordionOne">--%>
<%--                                        Accordion Item 1--%>
<%--                                    </button>--%>
<%--                                </h2>--%>

<%--                                <div id="accordionOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">--%>
<%--                                    <div class="accordion-body">--%>
<%--                                        Lemon drops chocolate cake gummies carrot cake chupa chups muffin topping. Sesame snaps icing marzipan gummi bears macaroon dragée danish caramels powder. Bear claw dragée pastry topping soufflé. Wafer gummi bears marshmallow pastry pie.--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="card accordion-item">--%>
<%--                                <h2 class="accordion-header" id="headingTwo">--%>
<%--                                    <button type="button" class="accordion-button collapsed" data-bs-toggle="collapse" data-bs-target="#accordionTwo" aria-expanded="false" aria-controls="accordionTwo">--%>
<%--                                        Accordion Item 2--%>
<%--                                    </button>--%>
<%--                                </h2>--%>
<%--                                <div id="accordionTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">--%>
<%--                                    <div class="accordion-body">--%>
<%--                                        Dessert ice cream donut oat cake jelly-o pie sugar plum cheesecake. Bear claw dragée oat cake dragée ice cream halvah tootsie roll. Danish cake oat cake pie macaroon tart donut gummies. Jelly beans candy canes carrot cake. Fruitcake chocolate chupa chups.--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="card accordion-item">--%>
<%--                                <h2 class="accordion-header" id="headingThree">--%>
<%--                                    <button type="button" class="accordion-button collapsed" data-bs-toggle="collapse" data-bs-target="#accordionThree" aria-expanded="false" aria-controls="accordionThree">--%>
<%--                                        Accordion Item 3--%>
<%--                                    </button>--%>
<%--                                </h2>--%>
<%--                                <div id="accordionThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">--%>
<%--                                    <div class="accordion-body">--%>
<%--                                        Oat cake toffee chocolate bar jujubes. Marshmallow brownie lemon drops cheesecake. Bonbon gingerbread marshmallow sweet jelly beans muffin. Sweet roll bear claw candy canes oat cake dragée caramels. Ice cream wafer danish cookie caramels muffin.--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="navs-pills-justified-messages" role="tabpanel">
                        <div class="accordion mt-3" id="accordionExample1">
                            <c:forEach  var="item" items="${listNotification}" varStatus="loop">
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
                                <div class="card accordion-item ${loop.index eq 0 ?'active':''}">
                                    <h2 class="accordion-header" id="heading${item.id}">
                                        <button type="button" class="accordion-button" data-bs-toggle="collapse" data-bs-target="#accordion${item.id}" aria-expanded="true" aria-controls="accordion${item.id}">
                                            <span class="badge bg-label-${bg} me-1">${item.dateStr}</span> : Giáo viên  ${item.classCourseModel.teacherModel.name}  thông báo đến lớp : ${item.classCourseModel.courseModel.name} (Nhóm:<span class="badge bg-label-${bg} me-1">${item.classCourseCode}</span>)
                                        </button>
                                    </h2>
                                    <div id="accordion${item.id}" class="accordion-collapse collapse ${loop.index eq 0 ?'show':''}" aria-labelledby="heading${item.id}" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            Giáo viên nhắn: Lớp <span class="badge bg-label-${item.content eq 'nghỉ học'?'danger':'primary'} me-1">${item.content}</span>(Tiết:${item.classCourseModel.startLesson}-${item.classCourseModel.endLesson}) ngày: ${item.dateStr}
                                            <c:if test="${item.classCourseModel.roomModel!=null and item.content eq 'học bù'}">
                                                phòng: <span class="badge bg-label-${bg} me-1">${item.classCourseModel.roomModel.areaCode}${item.classCourseModel.roomModel.roomCode}</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
