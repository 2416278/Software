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

    <title>支付页面</title>
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
                    <h5>支付平台</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="signupForm" >
                            <tr>
                                <th class="img1">
                                    <img src="img/p1.png" class="img1" id="a" style="width:30%;height:150px;"alt="银联支付">
                                </th>
                                <th >
                                    <img src="img/p2.jpg" class="img2" id="b" style="width:30%;height:150px;" alt="支付宝支付">
                                </th>
                                <th >
                                    <img src="img/p3.png" class="img3" id="c" style="width:30%;height:150px;" alt="微信支付">
                                </th>
                            </tr>

                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-success demo2" id="sumbitBtn" type="button" >提交</button>
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

<script src="/js/plugins/peity/jquery.peity.min.js"></script>
<script src="/js/content.min.js?v=1.0.0"></script>
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script src="/js/demo/peity-demo.min.js"></script>
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script>
    function resetPage(){
        window.location.href="/store/cartServlet?action=list";//跳转到列表
    }


    var a=document.querySelector(".img1");
    var b=document.querySelector(".img2");
    var c=document.querySelector(".img3");
    a.onclick=function (ev) {
        a.style.border="solid 4px black";
        b.style.border="none";
        c.style.border="none"
        $("#sumbitBtn").click(function () {
            alert("支付成功");
            window.location.href = "/store/cartServlet?action=list"; // 跳转到列表
        })
    }
    b.onclick=function (ev) {
        b.style.border="solid 4px black";
        a.style.border="none";
        c.style.border="none"
        $("#sumbitBtn").click(function () {
            alert("支付成功");
            window.location.href = "/store/cartServlet?action=list"; // 跳转到列表
        })
    }
    c.onclick=function (ev) {
        c.style.border="solid 4px black";
        b.style.border="none";
        a.style.border="none"
        $("#sumbitBtn").click(function () {
            alert("支付成功");
            window.location.href = "/store/cartServlet?action=list"; // 跳转到列表
        })
    }

</script>
</body>
</html>
