<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<%@ include file="../basicPage/basic.jsp" %>

</head>
  
  <input type="hidden" id="pageTotal" value="${page.pageTotal }">
<body>

    <div class="pd-5  mt-20" >
     	<span class="l"><a class=" btn btn-primary radius"  onclick="layer_show('添加','<%=basePath %>/menu/edit?parentId=${parentId }&isAdd=true',600,400)" ><i class="Hui-iconfont">&#xe610;</i>添加菜单</a></span>
		<span class="r"><strong>总条数：${page.total }</strong></span>
	</div>
   <table class="table table-border  table-bg table-bordered table-hover">
   		<thead  class="text-c">
   			<tr>
   			    <th>序号</th>
   				<th>菜单名称</th>
   				<th>排序</th>
   			<c:if test="${not empty parentId }">	
   				<th>url</th>
   			 </c:if>	
   				<th>操作</th>
   			</tr>
   		<c:forEach var="item" items="${page.rows }" varStatus="status">
   			<tr>
   				<td>${page.begin+status.count }</td>

   				<td>
   				  <c:if test="${fn:length(item.id) <= 3 }">
   				  <a id="parentId" _id="${item.id}" href="<%=basePath%>/menu/findChildMenu?parentId=${item.id}" style="color:blue">${item.name }</a>
   				  </c:if>
   				  <c:if test="${fn:length(item.id) >3 }">
   				    ${item.name }
   				  </c:if>
   				</td>
   				<td>${item.sort }</td>
   				<c:if test="${not empty parentId }">
   				 <td>${item.url }</td>
   			    </c:if>	
   				<td>
   					<a onclick="layer_show('修改','<%=basePath%>/menu/edit?id=${item.id }&isAdd=false',600,300)"><i class="Hui-iconfont">&#xe6df;</i>修改</a>
   					<a href='<%=basePath%>/menu/delete?id=${item.id }' style="margin-left: 5px"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
   				</td>
   			</tr>
   	  </c:forEach>
   		</thead>
   </table>
</body>
<script type="text/javascript">
</script>
</html>