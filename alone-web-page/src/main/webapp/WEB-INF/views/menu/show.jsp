<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
 <%@ include file="../basicPage/nav.jsp" %>
<div class="page-container">
    <div id="tableList" >
    	<jsp:include page="table.jsp"></jsp:include>
    </div>
    <div id="pagination" style="text-align: right;margin-top: 5px"></div>
  </div>
</body>
<script type="text/javascript">

$(function(){
	basePage.pageLayer(getParam());
})
function getParam(){
	var param = {
			data:{
			currentPageNumber:1
			},
		url:"../menu/searchPage",	
		tableList:"tableList",
		pagination:"pagination",
		pageTotal:$("#pageTotal").val()
	}
	return param;
}

</script>