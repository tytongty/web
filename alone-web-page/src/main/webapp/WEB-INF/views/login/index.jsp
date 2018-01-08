<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	  <%@ include file="../basicPage/basic.jsp" %>
	<input type="hidden" id="TenantId" name="TenantId" value="" />
	<div class="header"></div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form class="form form-horizontal" id="formId">
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-xs-8">
						<input id="username" name="username" type="text" placeholder="账户"
							class="input-text radius size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-xs-8">
						<input id="password" name="password" type="password"
							placeholder="密码" class="input-text radius size-L">
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input class="input-text size-L" id="code" type="text"
							placeholder="验证码" style="width: 150px;"> <img
							id="imageCode" src="../login/capture" onclick="changeCapture();">
						<a id="kanbuq" onclick="changeCapture();" href="javascript:;">看不清，换一张</a>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input name="submit" id="submit"
							class="btn btn-success radius size-S"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> <input
							name="cancel" id="cancel" class="btn btn-default radius size-S"
							value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">Copyright &copy; Alone Community v1.0</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#submit").click(function() {
			var username = $("#username").val();
			var password = $("#password").val();
			var code = $("#code").val();
			if (username == '') {
				layer.msg("用户名不能为空", {
					time : 1000
				})
				return false;
			}
			if (password == '') {
				layer.msg("密码不能为空", {
					time : 1000
				});
				return false;
			}
			if (code == '') {
				layer.msg("验证码不能为空", {
					time : 1000
				});
				return false;
			}
			$.ajax({
				type : "post",
				url : "../login/doLogin",
				dataType : "json",
				data : {
					username : username,
					password : password,
					code : code
				},
				success : function(data) {
					if (data.sucFlag == 1) {
						window.location.href = '../index/index'
					} else {
						layer.msg(data.msg, {
							time : 1000
						})
					}
				}
			})

		})
	})
	$("#cancel").click(function() {
		$("#username").val("");
		$("#password").val("");
	})
	function changeCapture() {
		var time = new Date().getTime();
		url = $("#imageCode").attr("src") + "?time=" + time;
		return $("#imageCode").attr("src", url);
	}
</script>
</html>