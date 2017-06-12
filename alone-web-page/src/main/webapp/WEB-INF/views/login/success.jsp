<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../basicPage/basic.jsp" %>
<%=basePath %>
</head>
<body>
<jsp:include page="../basicPage/header.jsp"/>
<jsp:include page="../basicPage/menu.jsp"/>

<section class="Hui-article-box">
	
	<div class="Hui-article">
		<article class="cl pd-20">
			这是个空白页，请在此处加入您的代码！
		</article>
	</div>
</section>
	<%-- 欢迎 ${user.username } 来到alone 社区         <a href="<%=basePath %>/login/loginOut">退出</a>
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
	</c:forEach> --%>
</body>
</html>