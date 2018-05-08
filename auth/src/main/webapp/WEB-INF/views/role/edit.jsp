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
                角色管理
                <%--<small>Blank example to the fixed layout</small>--%>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">角色管理</a></li>
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
                    <form:form method="post" commandName="role" cssClass="form-horizontal">
                        <form:hidden path="id"/>
                        <form:hidden path="available"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">角色名</label>
                                <div class="col-md-4">
                                    <form:input path="role" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">角色描述</label>
                                <div class="col-md-4">
                                    <form:input path="description" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">拥有的资源列表</label>
                                <div class="col-md-4">
                                    <form:hidden path="resourceIds"/>
                                    <textarea id="resourceName" name="resourceName" readonly class="form-control">${fn:resourceNames(role.resourceIds)}</textarea>
                                    <a id="menuBtn" href="javascript:;">选择</a>
                                    <div id="menuContent" class="menuContent" style="display:none; ">
                                        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
                                    </div>
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
    <script>
        jQuery(document).ready(function () {
            $(function () {
                var setting = {
                    check: {
                        enable: true ,
                        chkboxType: { "Y": "", "N": "" }
                    },
                    view: {
                        dblClickExpand: false
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onCheck: onCheck
                    }
                };

                var zNodes =[
                    <c:forEach items="${resourceList}" var="r">
                    <c:if test="${not r.rootNode}">
                    { id:${r.id}, pId:${r.parent_id}, name:"${r.name}", checked:${fn:in(role.resourceIds, r.id)}},
                    </c:if>
                    </c:forEach>
                ];

                function onCheck(e, treeId, treeNode) {
                    var zTree = $.fn.zTree.getZTreeObj("tree"),
                            nodes = zTree.getCheckedNodes(true),
                            id = "",
                            name = "";
                    nodes.sort(function compare(a,b){return a.id-b.id;});
                    for (var i=0, l=nodes.length; i<l; i++) {
                        id += nodes[i].id + ",";
                        name += nodes[i].name + ",";
                    }
                    if (id.length > 0 ) id = id.substring(0, id.length-1);
                    if (name.length > 0 ) name = name.substring(0, name.length-1);
                    $("#resourceIds").val(id);
                    $("#resourceName").val(name);
//                hideMenu();
                }

                function showMenu() {
                    var cityObj = $("#resourceName");
                    var cityOffset = $("#resourceName").offset();
                    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

                    $("body").bind("mousedown", onBodyDown);
                }
                function hideMenu() {
                    $("#menuContent").fadeOut("fast");
                    $("body").unbind("mousedown", onBodyDown);
                }
                function onBodyDown(event) {
                    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                        hideMenu();
                    }
                }

                $.fn.zTree.init($("#tree"), setting, zNodes);
                $("#menuBtn").click(showMenu);
            });
        });
    </script>
</body>
<!-- END BODY -->
</html>
