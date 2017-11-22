<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆界面</title>
<script type="text/javascript">
	
	function getVal(id) {
		return document.getElementById(id).value;
	}
	
	function getElement(id) {
		return document.getElementById(id);
	}
	
	 
	function check() {
		console.log(getVal('userName'))
		if (isEmpty(getVal('userName'))) {
			alert('请输入用户名!');
		}else if (isEmpty(getVal('pwd'))) {
			alert('请输入密码!');
		}
		return login.submit();
	}
	
	function isEmpty(id) {
			if (id==null||id=='') {
				return true;
			}
			return false;
	}
	
	function remove() {
		getElement('userName').value=null;
		getElement('pwd').value=null;
	}
</script>
<% 
	Cookie[] cookies=request.getCookies();
if(cookies!=null){
    String userName="";
    String pwd="";
    String remberMe="";
    for(Cookie cookie:cookies){
        if("userNameCookie".equals(cookie.getName())){
            userName=cookie.getValue();
        }else  if("pwdCookie".equals(cookie.getName())){
            pwd=cookie.getValue();
        }else  if("remberMeCookie".equals(cookie.getName())){
            remberMe=cookie.getValue();
        }
    }
}
%>
</head>
<body>
<form action="/login" method="post" id="login">
<table>
	<tr>
		<td><label>用户名:&nbsp;</label></td>
		<td><input id="userName" name="userName" type="text" value="${userName}"></td>
	</tr>
	<tr>
		<td><label>密码:&nbsp;</label></td>
		<td><input id="pwd" name="pwd" type="password" value="${pwd}"></td>
	</tr>
	<tr>
		<td>
			<button type="button" onclick="check()">登陆</button>
			<button type="button" onclick="remove()">清除</button>
		</td>
	</tr>
</table>
		
</form>
</body>
</html>