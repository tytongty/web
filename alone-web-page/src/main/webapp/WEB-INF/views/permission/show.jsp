<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../basicPage/basic.jsp" %>
</head>
<body>
<%@ include file="../basicPage/nav.jsp" %>
<div class="page-container">
	<div class="text-l">
	 <label class="ml-30">角色名称：</label>
	 <span class="select-box inline">
       <select class="select " size="1" id="roleId" name="roleId" style="width: 150px">
        <option value="" selected>请选择</option>
      </select>
      </span>
		<button  id="searchId" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		<button  id="permissionEdit" class="btn btn-success" type="submit">提交权限修改</button>
	</div>
	<div class="text-c">
	
	</div>
	 <div class="text-l">
		<table class="table">
			<tr>
				<td class="va-t">
					<ul id="treeDemo" class="ztree"></ul>
				</td>
			</tr>
		</table>
	 </div>
	
</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/myResources/js/permission.js"></script>
</html>