<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../basicPage/basic.jsp" %>

</head>
  
  <input type="hidden" id="pageTotal" value="${page.pageTotal }">
<body>
    <div>
     	<a class=" btn btn-primary radius" style="text-align: left;" onclick="layer_show('添加','<%=basePath %>/adminUser/edit',500,400)" ><i class="Hui-iconfont">&#xe610;</i>添加用户</a>
		<div style="text-align: right;"><label style="text-align: right;"><strong>总条数：${page.total }</strong></label> </div> 
	</div>
   <table class="table table-border  table-bg table-bordered table-hover">
   		<thead  class="text-c">
   			<tr>
   			    <th>序号</th>
   				<th>用户名</th>
   				<th>角色id</th>
   				<th>账户状态</th>
   				<th>操作</th>
   			</tr>
   		<c:forEach var="item" items="${page.rows }" varStatus="status">
   			<tr>
   				<td>${page.begin+status.count }</td>
   				<td>${item.username }</td>
   				<td>${item.roleId }</td>
   				<td>${item.locked }</td>
   				<td>
   					<a onclick="layer_show('修改','<%=basePath%>/adminUser/edit?userId=${item.userId }',600,400)"><i class="Hui-iconfont">&#xe6df;</i>修改</a>
   					<a href='<%=basePath%>/adminUser/delete?userId=${item.userId }' style="margin-left: 5px"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
   				</td>
   			</tr>
   	  </c:forEach>
   		</thead>
   </table>
</body>
</html>