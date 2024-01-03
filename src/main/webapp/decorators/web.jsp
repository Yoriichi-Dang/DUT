<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 01-Dec-23
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp"%>

<html lang="en" class="light-style layout-menu-fixed" dir="ltr" data-theme="theme-default" data-assets-path="<c:url value='/template/admin/assets/'/>" data-template="vertical-menu-template-free">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

  <title>DUT</title>

  <meta name="description" content="" />

  <!-- Favicon -->
  <link rel="icon" type="image/x-icon" href="<c:url value='/template/admin/assets/img/favicon/favicon.ico'/>" />

  <!-- Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet" />

  <!-- Icons. Uncomment required icon fonts -->
  <link rel="stylesheet" href="<c:url value='/template/admin/assets/vendor/fonts/boxicons.css'/>" />

  <!-- Core CSS -->
  <link rel="stylesheet" href="<c:url value='/template/admin/assets/vendor/css/core.css'/>" class="template-customizer-core-css" />
  <link rel="stylesheet"  href="<c:url value='/template/admin/assets/vendor/css/theme-default.css'/>"   class="template-customizer-theme-css"/>
  <link rel="stylesheet"  href="<c:url value='/template/admin/assets/css/demo.css'/>"/>
  <!-- Vendors CSS -->
  <link rel="stylesheet"  href="<c:url value='/template/admin/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css'/>"/>
  <link rel="stylesheet"  href="<c:url value='/template/admin/assets/vendor/libs/apex-charts/apex-charts.css'/>"/>
  <!-- Page CSS -->
  <!-- Helpers -->
  <script src="<c:url value='/template/admin/assets/vendor/js/helpers.js'/>"></script>

  <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
  <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
  <script src="<c:url value='/template/admin/assets/js/config.js'/>"></script>
<%--  <script src="<c:url value='/template/admin/assets/vendor/libs/jquery/jquery.js'/>"></script>--%>
</head>

<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar layout-without-menu">
  <div class="layout-container">
    <div class="layout-page">
      <!-- Navbar -->
      <%@ include file="/common/web/header.jsp"%>

      <!-- / Navbar -->

      <!-- Content wrapper -->
      <div class="content-wrapper">
        <!-- Content -->
        <dec:body/>
        <!-- / Content -->

        <!-- Footer -->
        <%@ include file="/common/web/footer.jsp"%>
        <!-- / Footer -->

        <div class="content-backdrop fade"></div>
      </div>
      <!-- Content wrapper -->
    </div>
    <!-- / Layout page -->
  </div>

  <!-- Overlay -->
  <div class="layout-overlay layout-menu-toggle"></div>
</div>

<!-- / Layout wrapper -->


<script src="<c:url value='/template/admin/assets/js/ui-toasts.js'/>"></script>
<!-- Core JS -->
<!-- build:js assets/vendor/js/core.js -->
<script src="<c:url value='/template/admin/assets/vendor/libs/popper/popper.js'/>"></script>
<script src="<c:url value='/template/admin/assets/vendor/js/bootstrap.js'/>"></script>
<script src="<c:url value='/template/admin/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js'/>"></script>
<script src="<c:url value='/template/admin/assets/vendor/js/menu.js'/>"></script>


<!-- endbuild -->

<!-- Vendors JS -->
<script src="<c:url value='/template/admin/assets/vendor/libs/apex-charts/apexcharts.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/main.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/extended-ui-perfect-scrollbar.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/dashboards-analytics.js'/>"></script>


<!-- Main JS -->

<!-- Page JS -->

<!-- endbuild -->
<%--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>--%>
<!-- Vendors JS -->
<!-- Main JS -->
<!-- Page JS -->
<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
