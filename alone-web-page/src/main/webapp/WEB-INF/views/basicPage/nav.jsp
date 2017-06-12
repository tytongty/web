<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">
</i> 
<c:forEach var ="parent" items="${parentPermissionList }">
  <a href="/" class="maincolor">${parent.name }</a>
 <c:forEach var = "child" items="${parent.childPermission }">
  <c:if test="${child.url}"></c:if>
 <span class="c-999 en">&gt;</span><span class="c-666"></span>
 </c:forEach>
</c:forEach>
</nav>

</body>
</html>