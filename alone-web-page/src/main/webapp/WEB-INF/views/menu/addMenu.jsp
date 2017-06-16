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
<body>
   <input type="hidden" value="${msg }" id="msg">
<div class="page-container">
	<form id="formId">
	   <input type="hidden" value="${parentId }" name="parentId">
		<table class="table table-border table-bg table-bordered table-hover">
			<thead>
			    <tr class="text-c">
					<th colspan="2">
					   菜单兰添加
					</th>
				</tr>
				<tr class="text-r">
					<td>id</td>
					<td><input type="text" name="id" class="input-text"></td>
				</tr>
				
				<tr class="text-r">
					<td>菜单名称</td>
					<td><input type="text" name="name" class="input-text"></td>
				</tr >
				<tr class="text-r">
					<td>sort</td>
					<td><input type="text" name="sort" class="input-text"></td>
				</tr>
			<c:if test="${not empty parentId }">
				<tr class="text-r">
					<td>url</td>
					<td><input type="text" name="url" class="input-text"></td>
				</tr>
			</c:if>
				<tr class="text-c">
					<td colspan="2">
						<input class="btn btn-primary radius" type="submit" value="submit" id="submit">
					</td>
				</tr>
			</thead>
		</table>
	</form>
 </div>	
</body>
<script type="text/javascript">
$(function(){
	if($("#msg").val()){
		layer.alert($("#msg").val(),function(index){
			layer.close(index);
			parent.location.reload();
		})
	}
})
	$("#submit").click(function(){
		$("#formId").attr({"action":"../menu/saveMenu","method":"post"});
		$("#formId").submit();
	})
</script>
</html>