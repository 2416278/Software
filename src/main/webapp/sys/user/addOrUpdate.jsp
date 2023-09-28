<%--
  Created by IntelliJ IDEA.
  User: hxx
  Date: 2023/7/13
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/form_validate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户管理</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <script src="/js/jquery.js"></script>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>添加数据</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="form_basic.html#">选项1</a>
                            </li>
                            <li><a href="form_basic.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="signupForm" action="/sys/userServlet?action=saveOrUpdate" method="post">
                        <input type="hidden" name="id" value="${entity.uid}">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户名</label>
                            <div class="col-sm-8">
                                <input id="usename" name="usename" value="${entity.usename}" class="form-control" required="" type="text">
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i></span><span style="color:red;margin-left:20px;">${msg}</span>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户级别</label>
                            <div class="col-sm-8">
                                <select id="grade" name="grade" class="form-control" required="" type="text" >
                                    <option value="超级管理员">超级管理员</option>
                                    <option value="金牌客户">金牌客户</option>
                                    <option value="银牌客户">银牌客户</option>
                                    <option value="铜牌客户">铜牌客户</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">创建日期</label>
                            <div class="col-sm-8">
                                <input type="date" id="createTime" value="${entity.createTime}" name="createTime" required=""value-format="yyyy-MM-dd" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">消费总金额：</label>
                            <div class="col-sm-8">
                                <input id="total" name="total" value="${entity.total}" class="form-control" required="" data-mask="&yen; 999,999,999.99" type="text" aria-required="true" aria-invalid="true" class="error">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电话：</label>
                            <div class="col-sm-8">
                                <input id="phone" name="phone" value="${entity.phone}"minlength="11" required="" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">E-mail：</label>
                            <div class="col-sm-8">
                                <input id="email" name="email" value="${entity.email}"class="form-control" type="email">
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" id="btn" type="submit">提交</button>
                                <button class="btn btn-default" onclick="resetPage()" type="button">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="//js/jquery.min.js?v=2.1.4"></script>
<script src="//js/bootstrap.min.js?v=3.3.6"></script>
<script src="//js/content.min.js?v=1.0.0"></script>
<script src="//js/plugins/validate/jquery.validate.min.js"></script>
<script src="//js/plugins/validate/messages_zh.min.js"></script>
<script src="//js/demo/form-validate-demo.min.js"></script>

<script>
    function resetPage(){
        window.location.href="/sys/userServlet?action=list";//跳转到列表
    }
    var selectElement = document.getElementById("grade");
    var selectedValue = "${entity.grade}";
    selectElement.value=selectedValue;
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>
