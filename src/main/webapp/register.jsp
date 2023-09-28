<%--
  Created by IntelliJ IDEA.
  User: hxx
  Date: 2023/7/12
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<!DOCTYPE html>
<html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">


  <title>注册</title>
 <link href="/css/bootstrap.min.css" rel="stylesheet">
  <link href="/css/font-awesome.min.css" rel="stylesheet">
  <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
  <link href="/css/animate.min.css" rel="stylesheet">
  <link href="/css/style.min.css" rel="stylesheet">
  <script>if(window.top !== window.self){ window.top.location = window.location;}</script>

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen   animated fadeInDown">
  <div>

    <h3>欢迎注册校园购物系统</h3>
    <p>创建一个新账户</p>
    <form class="m-t" role="form" action="/sys/registerServlet" method="post">
      <div class="form-group">
        <label>

          <input type="text" class="form-control" id="name" name="name" value="${entity.name}" placeholder="请输入用户名" required="">
        </label>
      </div>
      <div class="form-group">
        <label>
          <input type="password" class="form-control" id="password" name="password" value="${entity.password}" placeholder="请输入密码" required="">
        </label>
      </div>
      <div class="form-group">
        <label>
          <input type="password" class="form-control" id="passwordSub" name="passwordSub"  placeholder="请再次输入密码" required="">
        </label>
      </div>
      <div class="form-group">
        <label>
          <input type="email" class="form-control" placeholder="邮箱" id="email" name="email" value="${entity.email}" required="">
        </label>
      </div>
      <div class="form-group">
        <span class="help-block m-b-none"><i class="fa fa-info-circle"></i></span><span style="color:red;margin-left:10px;">${msg}</span>
      </div>

      <button type="submit" class="btn btn-primary block full-width m-b">注 册</button>

      <p class="text-muted text-center"><small>已经有账户了？</small><a href="/login.jsp">点此登录</a>
      </p>

    </form>
  </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script>
  $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>

</body>

</html>
