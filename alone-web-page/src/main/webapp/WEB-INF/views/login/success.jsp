<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
      String 	basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	欢迎 ${user.username } 来到alone 社区         <a href="<%=basePath %>/login/loginOut">退出</a>
	<c:forEach var ="parent" items="${parentPermissionList }">
	  <ul>
		<li>
		  ${parent.name }
		  <c:forEach var = "child" items="${parent.childPermission }">
		       <ul>
		  	     <li><a href="${child.url }">${child.name }</a></li>
		      </ul>
		  </c:forEach>
		</li>
	 </ul>
	</c:forEach>
</body>
</html>