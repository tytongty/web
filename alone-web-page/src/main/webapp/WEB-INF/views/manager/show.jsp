<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../basicPage/basic.jsp" %>
</head>
<%@ include file="../basicPage/nav.jsp" %>
<body>
<div class="page-container">
<div class="row cl" style="margin-top: 10px;margin-left: 20px">
  <form id="formId">
     <input type="text" id="message"  class="input-text" style="width: 150;" name="message">
     <input class ="btn btn-primary radius" type="button" onclick="createQCode();" value="生成二维码">
  </form>
</div>
 <c:if test="${not empty pathName }">
    <jsp:include page="table.jsp"></jsp:include>
 </c:if>   
 </div> 
</body>
<script type="text/javascript">
  function createQCode(){
	  if($("#message").val() == ''){
		  layer.alert("生成二维码的信息不能为空")
	  }else{
		  $("#formId").attr("action","../manager/show");
		  $("#formId").submit();
	  }
  }
 

</script>
