<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>最近停泊</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<!--数据展示头部-->
<div class="box-header with-border">
    <h3 class="box-title">最近停泊</h3>
</div>
<!--数据展示头部-->
<!--数据展示内容区-->
<div class="box-body">
    <!-- 数据表格 -->
    <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
        <thead>
        <tr>
            <th class="sorting_asc">船舶识别号</th>
            <th class="sorting">船只船长</th>
            <th class="sorting">船只吨位</th>
            <th class="sorting">船只型号</th>
            <th class="sorting">船只状态</th>
            <th class="sorting">船只当前位置</th>
            <th class="sorting">船只进港停泊时间</th>
            <th class="sorting">船只出港时间</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageResult.rows}" var="ship">
            <tr>
                <td>${ship.sin}</td>
                <td>${ship.captainName}</td>
                <td><c:if test="${ship.tonnage ==0}">小型</c:if>
                    <c:if test="${ship.tonnage ==1}">中型</c:if>
                    <c:if test="${ship.tonnage ==2}">大型</c:if></td>
                <td>${ship.model}</td>
                <td>
                    <c:if test="${ship.status ==0}">已出港</c:if>
                    <c:if test="${ship.status ==1}">停泊中</c:if>
                    <c:if test="${ship.status ==2}">登记出港</c:if>
                </td>
                <td>${ship.currentLocation}</td>
                <td>${ship.berthTime}</td>
                <td>${ship.exitTime}</td>
                <td class="text-center">
                    <c:if test="${ship.status ==0}">
                        <button type="button" class="btn bg-blue-gradient btn-xs" data-toggle="modal" data-target="#shipModal"
                                onclick="findShipById(${ship.id},'berth')"> 登记入港
                        </button>
                    </c:if>
                    <c:if test="${ship.status ==1 ||ship.status ==2}">
                        <button type="button" class="btn bg-blue-gradient btn-xs" disabled="true">登记出港</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 数据表格 /-->
</div>
<!-- 数据展示内容区/ -->
<%--引入存放模态窗口的页面--%>
<jsp:include page="/admin/ship_modal.jsp"></jsp:include>
</body>
</html>
