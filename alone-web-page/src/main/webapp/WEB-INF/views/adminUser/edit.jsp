<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../basicPage/basic.jsp"%>
</head>
<body>
<input type="hidden" value="${msg }" id="msg">
	<div class="page-container">
		<form id="formId">
		  <input type="hidden" value="${userId }" id="userId" name="userId">
			<table class="table table-border table-bg table-bordered table-hover">

				<c:choose>
					<c:when test="${not empty userId }">
						<thead>
							<tr class="text-c">
								<th colspan="2">修改用户</th>
							</tr>
						</thead>
						<tr class="text-r">
							<td><label>用户名:</label></td>
							<td><input type="text" class="input-text" name="username"
								placeholder="输入用户名" value="${userEdit.username }"></td>
						</tr>
						<tr class="text-c">
							<td colspan="2"><input class="btn btn-primary radius" id="submitId"
								type="submit" value="save edit"></td>
						</tr>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${ empty userId }">
						<thead>
							<tr class="text-c">
								<th colspan="2">添加用户</th>
							</tr>
						</thead>
						<tr class="text-r">
							<td><label>用户名:</label></td>
							<td><input type="text" class="input-text" name="username"
								placeholder="输入用户名"></td>
						</tr>
						<tr class="text-r">
							<td><label>密码:</label></td>
							<td><input type="password" class="input-text"
								name="password" placeholder="输入密码"></td>
						</tr>
						<tr class="text-c">
							<td colspan="2"><input class="btn btn-primary radius"
								type="submit" value="submit" id="submitId"></td>
						</tr>

					</c:when>
				</c:choose>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">

	$(function() {
		if ($("#msg").val().length > 0) {
		 layer.alert($("#msg").val(),function(index){
			 layer.close(index)
			 parent.location.reload();
		 });
			
		}
	})

	$("#submitId").click(function() {
		$("#formId").attr("action", "../adminUser/saveOrUpdate");
		$("#formId").submit();
	})
</script>
</html>