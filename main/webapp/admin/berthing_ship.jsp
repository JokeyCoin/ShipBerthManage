<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>当前停泊</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<!-- .box-body -->
<div class="box-header with-border">
    <h3 class="box-title">当前停泊</h3>
</div>
<div class="box-body">
    <!--工具栏 数据搜索 -->
    <div class="box-tools pull-right">
        <div class="has-feedback">
            <form action="${pageContext.request.contextPath}/ship/searchBerthing" method="post">
                船只识别号：<input name="sin" value="${search.sin}">&nbsp&nbsp&nbsp&nbsp
                船只船长：<input name="captainName" value="${search.captainName}">&nbsp&nbsp&nbsp&nbsp
                船只吨位：<input name="tonnage" value="${search.tonnage}">&nbsp&nbsp&nbsp&nbsp
                <input class="btn btn-default" type="submit" value="查询">
            </form>
        </div>
    </div>
    <!--工具栏 数据搜索 /-->
    <!--数据列表-->
    <div class="table-box">
        <!-- 数据表格 -->
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th class="sorting_asc">船舶识别号</th>
                <th class="sorting">船只船长</th>
                <th class="sorting">船只吨位</th>
                <th class="sorting">净重</th>
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
                    <td>
                        <c:if test="${ship.tonnage ==0}">小型</c:if>
                        <c:if test="${ship.tonnage ==1}">中型</c:if>
                        <c:if test="${ship.tonnage ==2}">大型</c:if>
                    </td>
                    <td>${ship.netWeight}</td>
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
                        <c:if test="${ship.status ==1}">
                            <button type="button" class="btn bg-blue-gradient btn-xs" onclick="exitShip(${ship.id})">登记出港
                            </button>
                        </c:if>
                        <c:if test="${ship.status ==2}">
                            <button type="button" class="btn bg-blue-gradient btn-xs" disabled="true">登记出港中</button>
                            <c:if test="${USER_SESSION.role =='ADMIN'}">
                                <button type="button" class="btn bg-blue-gradient btn-xs" onclick="exitConfirm(${ship.id})">
                                    出港确认
                                </button>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- 数据表格 /-->
        <%--分页插件--%>
        <div id="pagination" class="pagination"></div>
    </div>
    <!-- 数据表格 /-->
</div>
<!-- /.box-body -->
</body>
<script>
	/*分页插件展示的总页数*/
    pageargs.total = Math.ceil(${pageResult.total})/pageargs.pagesize;
	/*分页插件当前的页码*/
    pageargs.cur = ${pageNum}
	/*分页插件页码变化时将跳转到的服务器端的路径*/
	pageargs.gourl = "${gourl}"
	/*保存搜索框中的搜索条件，页码变化时携带之前的搜索条件*/
    shipVO.sin = "${search.sin}"
    shipVO.captainName = "${search.captainName}"
    shipVO.tonnage = "${search.tonnage}"
	/*分页效果*/
    pagination(pageargs);
</script>
</html>