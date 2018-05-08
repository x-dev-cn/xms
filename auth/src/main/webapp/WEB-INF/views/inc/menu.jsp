<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="fn" uri="http://x-dev.cn/auth/tags/functions" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">主菜单</li>
            <li class="active treeview">
                <a href="<%=basePath%>">
                    <i class="fa fa-dashboard"></i> <span>首页</span>
                </a>
            </li>
            <c:forEach items="${menus}" var="m">
                <c:if test="${m.parent_id==1}">
                    <li class="treeview">
                        <a href="${m.url}">
                            <i class="fa fa-list"></i>
                            <span>${m.name}</span>
                            <c:if test="${m.available}">
                                <i class="fa fa-angle-left pull-right"></i>
                            </c:if>
                        </a>
                        <c:if test="${m.available}">
                            <ul class="treeview-menu">
                                <c:forEach items="${menus}" var="m2">
                                    <c:if test="${m2.parent_id==m.id}">
                                        <li>
                                            <a href="${m2.url}">
                                                <i class="fa fa-circle-o"></i> ${m2.name}
                                                <c:if test="${m2.available}">
                                                    <i class="fa fa-angle-left pull-right"></i>
                                                </c:if>
                                            </a>
                                            <c:if test="${m2.available}">
                                                <ul class="treeview-menu">
                                                    <c:forEach items="${menus}" var="m3">
                                                        <c:if test="${m3.parent_id==m2.id}">
                                                            <li>
                                                                <a href="${m3.url}">
                                                                    <i class="fa fa-circle-o"></i> ${m3.name}
                                                                    <c:if test="${m3.available}">
                                                                        <i class="fa fa-angle-left pull-right"></i>
                                                                    </c:if>
                                                                </a>
                                                                <c:if test="${m3.available}">
                                                                    <ul class="treeview-menu">
                                                                        <c:forEach items="${menus}" var="m4">
                                                                            <c:if test="${m4.parent_id==m3.id}">
                                                                                <li>
                                                                                    <a href="${m4.url}">
                                                                                        <i class="fa fa-circle-o"></i> ${m4.name}
                                                                                    </a>
                                                                                </li>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </ul>
                                                                </c:if>
                                                            </li>
                                                        </c:if>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:if>
            </c:forEach>
            <shiro:hasAnyRoles name="admin">
                <li class="treeview">
                    <a href="${pageContext.request.contextPath}/druid/index.html" target="_blank">
                        <i class="fa fa-bar-chart"></i><span>系统监控</span></a>
                </li>
            </shiro:hasAnyRoles>
            <!-- END ANGULARJS LINK -->
            <!-- 测试权限控制 -->
            <%--<shiro:hasAnyRoles name="admin">--%>
            <%--<li>--%>
            <%--<a href="javascript:;">super_admin 拥有此角色</a>--%>
            <%--</li>--%>
            <%--</shiro:hasAnyRoles>--%>

            <%--<shiro:hasPermission name="user:create">--%>
            <%--<li>--%>
            <%--<a href="javascript:;">user:create 拥有此权限</a>--%>
            <%--</li>--%>
            <%--</shiro:hasPermission>--%>

            <%--<shiro:hasPermission name="user:update">--%>
            <%--<li>--%>
            <%--<a href="javascript:;">user:update 拥有此权限</a>--%>
            <%--</li>--%>
            <%--</shiro:hasPermission>--%>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>