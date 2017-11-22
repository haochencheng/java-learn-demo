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
    <title>MDM主数据管理系统</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signin.css">
<script src="${pageContext.request.contextPath}/static/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
<% 
Cookie[] cookies=request.getCookies();
if(cookies!=null){
    String checked="";
    for(Cookie cookie:cookies){
        if("rememberMe_cookie".equals(cookie.getName())&&"deleteMe".equals(cookie.getValue())){
            checked="checked";
        }
    }
}

%>
</script>
<body>
<div class="form-signin-heading">
    <h2 style="margin-bottom: 10px">登陆</h2>
</div>
<div class="container">
    <form class="form-signin" role="form" method="post" action="${pageContext.request.contextPath}/home.html">
        用户名
        <input type="text" name="userName" value="${user.userName}" class="form-control" placeholder="请输入用户名" required autofocus >
        密码
        <input type="password" name="password" value="${user.password}" class="form-control" placeholder="请输入密码" required>
        <div class="btn-error">
            <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
            <%--<button class="btn btn-lg btn-primary logout" type="button">退出</button>--%>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox"  name="rememberMe" ${checked}> 记住密码
            </label>
            <label class="error">
                ${errorInfo}
            </label>
        </div>
    </form>

</div> <!-- /container -->


</body>
</html>