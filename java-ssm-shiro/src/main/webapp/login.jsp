<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="rolesList" type="java.util.List"--%>
<%--@elvariable id="roleList" type="java.util.List"--%>
<%--@elvariable id="user.userName" type="ognl"--%>
<%--@elvariable id="user.password" type="ognl"--%>
<%--@elvariable id="errorInfo" type="ognl"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Crm客户关系管理系统</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signin.css">
<script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<body>
<div id="con"></div>
<div class="container">

    <form class="form-signin" role="form" method="post" action="${pageContext.request.contextPath}/login.do">
        <h2 class="form-signin-heading">登陆</h2>
        <input type="text" name="userName" value="${user.userName}" class="form-control" placeholder="请输入用户名" required autofocus >
        <input type="password" name="password" value="${user.password}" class="form-control" placeholder="请输入密码" required>
            <label>
                <select title="selectUserType" class="select" name="roleId">
                    <option disabled selected hidden>请选择用户类型...</option>
                    <c:forEach var="role" items="${roleList}">
                        <option  value="${role.roleId}">${role.roleName}</option>
                    </c:forEach>
                </select>
            </label>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="1" name="rememberMe"> 记住密码
            </label>
            <span class="error">
               ${errorInfo}
            </span>
        </div>
        <div class="btn-error">
            <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
            <%--<button class="btn btn-lg btn-primary logout" type="button">退出</button>--%>
        </div>
    </form>

</div> <!-- /container -->


</body>
</html>