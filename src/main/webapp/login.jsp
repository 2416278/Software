<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

	<title>校园购物系统-登录</title>
	<link href="/css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/font-awesome.min.css" rel="stylesheet">
	<link href="/css/animate.min.css" rel="stylesheet">
	<link href="/css/style.min.css" rel="stylesheet">
	<link href="/css/login.min.css" rel="stylesheet">
	<!--[if lt IE 9]>
	<meta http-equiv="refresh" content="0;ie.html" />
	<![endif]-->
	<script>
		if(window.top!==window.self){window.top.location=window.location};
	</script>

</head>

<body class="signin">
<div class="signinpanel">
	<div class="row">
		<div class="col-sm-7">
			<div class="signin-info">
				<div class="logopanel m-b">
					<h1>校园购物系统</h1>
				</div>
				<div class="m-b"></div>
				<h4>欢迎使用 <strong>校园购物系统</strong></h4>
				<ul class="m-b">
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一:种类丰富</li>
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二:方便快捷</li>
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三:个性服务</li>
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四:正品保证</li>
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五:售后服务</li>
				</ul>
				<strong>还没有账号？ <a href="/register.jsp">立即注册&raquo;</a></strong>
			</div>
		</div>
		<div class="col-sm-5">
			<form method="post" action="/sys/loginServlet">
				<h4 class="no-margins">登录：</h4>
				<p class="m-t-md">登录到校园购物系统</p>
				<input type="text" name="userName" class="form-control uname" placeholder="用户名" />
				<input type="password" name="password" class="form-control pword m-b" placeholder="密码" />
				<a href="/sys/resetServlet?action=forgetPage">忘记密码了？</a><span style="color:red;margin-left:20px;">${msg}</span>
				<button class="btn btn-success btn-block">登录</button>
			</form>
		</div>
	<div class="signup-footer">
		<div class="pull-left">
			&copy; 2023
		</div>
	</div>
</div>
</body>

</html>
