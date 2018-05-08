<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                授权管理
                <%--<small>Blank example to the fixed layout</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">授权管理</a></li>
                <li class="active">${op}</li>
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
                    <h3 class="box-title">${op}</h3>
                </div>
                <div class="box-body">
                    <!-- BEGIN FORM-->
                    <form:form method="post" commandName="authorization" cssClass="form-horizontal">
                        <input id="id" name="id" type="hidden" value="${authorization.id}"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">应用</label>
                                <div class="col-md-4">
                                    <form:select path="app_id" items="${appList}" itemLabel="name" itemValue="id" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">用户</label>
                                <div class="col-md-4">
                                    <form:select path="user_id" items="${userList}" itemLabel="username" itemValue="id" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">角色列表</label>
                                <div class="col-md-4">
                                    <form:select cssClass="form-control" path="roleIds" items="${roleList}" itemLabel="description" itemValue="id" multiple="true"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions">
                            <div class="row">
                                <div class="col-md-offset-3 col-md-9">
                                    <button type="submit" class="btn btn-circle blue">${op}</button>
                                </div>
                            </div>
                        </div>
                    </form:form>
                    <!-- END FORM-->
                </div>
                <!-- /.box-body -->
                <div class="box-footer hidden">
                    Footer
                </div>
                <!-- /.box-footer-->
            </div>
            <!-- /.box -->

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