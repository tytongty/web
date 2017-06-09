<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
      String 	basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  	<form action="<%=basePath %>/login/registerSave" method="post">
	<label>用户名:</label><input type="text" name="username" placeholder="输入用户名">
	<br>
	<label>密码:</label><input type="password" name="password" placeholder="输入密码">
	<input type="submit" value="submit">
	</form>
</body>
</html>