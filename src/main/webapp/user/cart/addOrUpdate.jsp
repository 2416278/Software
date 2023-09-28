<%--
  Created by IntelliJ IDEA.
  User: hxx
  Date: 2023/7/13
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>添加商品</title>
    <link rel="shortcut icon" href="favicon.ico"><link href="/css/bootstrap.min.css?V-3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
    <script src="/js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="/css/demo/webuploader-demo.css">

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
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <%
                    String mess=(String)session.getAttribute("msg");  //接收后台传来的message
                    if(mess!=null&&!mess.equals("")){  //判断message
                %>
                <script type="text/javascript">
                    alert("<%=mess%>");  //弹出警示框
                </script>
                <%
                        session.setAttribute("msg","");  //将message值设为空，否则将一直弹出。
                    }
                %>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="signupForm" action="/store/cartServlet?action=saveOrUpdate" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品编号</label>
                            <div class="col-sm-8">
                                <input id="id" name="id" value="${entity.id}" class="form-control" required="" type="text">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">购买数量</label>
                            <div class="col-sm-8">
                                <input id="quantity" name="quantity" value="${entity.amount}" class="form-control" required="" type="text">
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
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.7"></script>
<script src="/js/content.min.js?v=1.0.0"></script>

<script type="text/javascript">
    var BASE_URL='/js/plugins/webuploader';
</script>
<script src="/js/plugins/webuploader/webuploader.min.js"></script>
<script src="/js/demo/webuploader-demo.js"></script>

<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>
<script src="/js/demo/form-validate-demo.js"></script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script>
    function resetPage(){
        window.location.href="/store/cartServlet?action=list";//跳转到列表
    }
</script>

</body>
</html>
