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
                资源管理
                <%--<small>Blank example to the fixed layout</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">资源管理</a></li>
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
                    <table class="table table-bordered" id="table">
                        <tr>
                            <th>名称</th>
                            <th>类型</th>
                            <th>URL路径</th>
                            <th>权限字符串</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${menuList}" var="resource">
                            <tr data-tt-id='${resource.id}'
                                <c:if test="${not resource.rootNode}">data-tt-parent-id='${resource.parent_id}'</c:if>>
                                <td>${resource.name}</td>
                                <td>${resource.type}</td>
                                <td>${resource.url}</td>
                                <td>${resource.permission}</td>
                                <td>
                                    <shiro:hasPermission name="sys:resource:create">
                                        <c:if test="${resource.type ne 'button'}">
                                            <a href="${pageContext.request.contextPath}/resource/${resource.id}/appendChild">添加子节点</a>
                                        </c:if>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="sys:resource:update">
                                        <a href="${pageContext.request.contextPath}/resource/${resource.id}/update">修改</a>
                                    </shiro:hasPermission>
                                    <c:if test="${not resource.rootNode}">
                                        <shiro:hasPermission name="sys:resource:delete">
                                            <a class="deleteBtn" href="javascript:;" data-id="${resource.id}">删除</a>
                                        </shiro:hasPermission>
                                    </c:if>
                                </td>
                            </tr>
                            <c:forEach items="${buttonList}" var="button">
                                <c:if test="${button.parent_id == resource.id}">
                                <tr data-tt-id='${button.id}'
                                    <c:if test="${not button.rootNode}">data-tt-parent-id='${button.parent_id}'</c:if>>
                                    <td>${button.name}</td>
                                    <td>${button.type}</td>
                                    <td>${button.url}</td>
                                    <td>${button.permission}</td>
                                    <td>
                                        <shiro:hasPermission name="sys:button:create">
                                            <c:if test="${button.type ne 'button'}">
                                                <a href="${pageContext.request.contextPath}/resource/${button.id}/appendChild">添加子节点</a>
                                            </c:if>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="sys:resource:update">
                                            <a href="${pageContext.request.contextPath}/resource/${button.id}/update">修改</a>
                                        </shiro:hasPermission>
                                        <c:if test="${not button.rootNode}">
                                            <shiro:hasPermission name="sys:resource:delete">
                                                <a class="deleteBtn" href="javascript:;" data-id="${button.id}">删除</a>
                                            </shiro:hasPermission>
                                        </c:if>
                                    </td>
                                </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
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

    <script>
        jQuery(document).ready(function () {
            $("#table").treetable({expandable: true}).treetable("expandNode", 1);
            $(".deleteBtn").click(function () {
                if (confirm("确认删除吗?")) {
                    location.href = "${pageContext.request.contextPath}/resource/" + $(this).data("id") + "/delete";
                }
            });
        });
    </script>
</body>
<!-- END BODY -->
</html>
