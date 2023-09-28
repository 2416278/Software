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

    <title>商品展示</title>
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
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#">选项1</a>
                            </li>
                            <li><a href="#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="signupForm" action="/store/showServlet?action=saveOrUpdate" method="post">
                        <input type="hidden" name="id" value="${entity.uid}">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品名称</label>
                            <div class="col-sm-8">
                                <input id="usename" name="usename" value="${entity.name}" class="form-control" required="" type="text">
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i></span><span style="color:red;margin-left:20px;">${msg}</span>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品价格</label>
                            <div class="col-sm-8">
                                <input type="date" id="createTime" value="${entity.sellprice}" name="createTime" required=""value-format="yyyy-MM-dd" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="img" id="imgName">
                            <label class="col-sm-3 control-label">图片：</label>
                            <div class="col=sm-8">
                                <!--dom结构部分-->
                                <div id="uploader-demo">
                                    <!--用来存放item-->
                                    <div id="fileList" class="uploader-list"></div>
                                    <div id="filePicker">选择图片</div>
                                    <span id="msg"></span>
                                </div>
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
        window.location.href="/store/showServlet?action=list";//跳转到列表
    }

    // 初始化Web Uploader
    var uploader = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: BASE_URL + '/js/Uploader.swf',

        // 文件接收服务端。
        server: '/sys/uploadServlet',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });

    uploader.on('uploadSuccess',function (file,data) {
        $( '#'+file.id ).addClass('upload-state-done');
        $("#imgName").val(data._raw);
        $("#fileList").text(data._raw)
    })
</script>

</body>
</html>
