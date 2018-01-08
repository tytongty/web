<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码</title>
<style type="text/css">
.imgBox{ width:200px;  
         height:200px;  
         text-align: right;}  
 </style>  

</head>
<body>
  <div  style="width: 250px;height: 250px;">
	  <img src="../static/myResources/images/${pathName}" />  
  </div>

</body>
</html>