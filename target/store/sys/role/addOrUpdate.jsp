<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>角色管理</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.min862f.css?v=4.1.0" rel="stylesheet">


    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>修改数据</h5>
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
                    <form class="form-horizontal m-t" id="signupForm" action="/sys/roleServlet?action=saveOrUpdate" method="post">
                        <input type="hidden" name="id" value="${entity.id}">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">账号</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" value="${entity.name}" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">密码</label>
                            <div class="col-sm-8">
                                <input id="password" name="password" value="${entity.password}" class="form-control" type="text">
                            </div>
                        </div>

                        <c:if test="${not empty entity }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分配菜单</label>
                                <div class="col-sm-8">
                                    <c:forEach items="${menus}" var="parent">
                                        <c:if test="${parent.parentId==-1}">
                                            <div class="checkbox i-checks">
                                                <label>
                                                    <input type="checkbox" ${parent.check?'checked':''} name="menuId" value="${parent.id}"><i></i>${parent.name}</label>                                </label>
                                            </div>
                                            <c:forEach items="${menus}" var="sub">
                                                <c:if test="${parent.id==sub.parentId}">
                                                    <div class="checkbox i-checks">
                                                        <label>
                                                          &nbsp;&nbsp;&nbsp;&nbsp;
                                                            <input type="checkbox" ${sub.check?'checked':''} name="menuId" value="${sub.id}">
                                                            <i></i>${sub.name}</label>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>

                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
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
    $(document).ready(function(){
        $(".i-checks").iCheck({
            checkboxClass:"icheckbox_square-green",
            radioClass:"iradio_square-green",})
    });

    function resetPage(){
        window.location.href="/sys/roleServlet?action=list";//跳转到列表
    }
    var selectElement = document.getElementById("notes");
    var selectedValue = "${entity.notes}";
    selectElement.value=selectedValue;
</script>
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>
