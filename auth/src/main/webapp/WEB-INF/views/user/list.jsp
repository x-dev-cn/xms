<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="fn" uri="http://x-dev.cn/auth/tags/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>系统管理</title>
    <%@ include file="../inc/style.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@ include file="../inc/head.jsp" %>
    <%@ include file="../inc/menu.jsp" %>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <%--<small>Blank example to the fixed layout</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">用户管理</a></li>
                <li class="active">列表</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <c:if test="${not empty msg}">
                <div class="callout callout-danger">
                        ${msg}
                </div>
            </c:if>
            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="btn-group">
                                    <shiro:hasPermission name="sys:user:create">
                                        <a class="btn btn-block btn-primary" href="${pageContext.request.contextPath}/user/create">用户新增<i class="fa fa-fw fa-plus"></i></a><br/>
                                    </shiro:hasPermission>
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <tr>
                            <th>用户名</th>
                            <th>所属组织</th>
                            <th>操作</th>
                        </tr>
                        <tbody>
                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <td>${user.username}</td>
                                <td>${fn:organizationName(user.organization_id)}</td>
                                <td>
                                    <shiro:hasPermission name="sys:user:update">
                                        <a href="${pageContext.request.contextPath}/user/${user.id}/update">修改</a>
                                    </shiro:hasPermission>

                                    <shiro:hasPermission name="sys:user:delete">
                                        <a href="${pageContext.request.contextPath}/user/${user.id}/delete">删除</a>
                                    </shiro:hasPermission>

                                    <shiro:hasPermission name="sys:user:update">
                                        <a href="${pageContext.request.contextPath}/user/${user.id}/changePassword">改密</a>
                                    </shiro:hasPermission>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <!-- BEGIN FOOTER -->
    <%@ include file="../inc/footer.jsp" %>
    <!-- END FOOTER -->
    <%@ include file="../inc/script.jsp" %>
</body>
<!-- END BODY -->
</html>