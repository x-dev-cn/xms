<%--
  Created by IntelliJ IDEA.
  User: felix
  Date: 2018/5/8
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>admin-demo</title>
</head>
<body>
<!-- 测试权限控制 -->
<shiro:hasAnyRoles name="admin">
super_admin 拥有此角色<br/>
</shiro:hasAnyRoles>

<shiro:hasPermission name="sys:user:create">
user:create 拥有此权限<br/>
</shiro:hasPermission>

<shiro:hasPermission name="sys:user:update">
user:update 拥有此权限<br/>
</shiro:hasPermission>
</body>
</html>
