<%--
  Created by IntelliJ IDEA.
  User: hxx
  Date: 2023/7/13
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品管理</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <c:forEach items="${requestScope.pageUtils.list}" var="entity">
                        <li class="">
                            <a data-toggle="tab" class="tab-${entity.id}" href="tab-${entity.id}" aria-expanded="true"> ${entity.name}</a>
                        </li>
                    </c:forEach>
                </ul>
                <div class="tab-content">
                    <c:forEach items="${requestScope.pageUtils.list}" var="entity">
                        <div id="tab-${entity.id}"  class="tab-pane">
                            <div class="panel-body">
                                <div class="row">
                                    <c:forEach items="${entity.goods}" var="good">
                                        <div class="col-sm-4">
                                            <div class="contact-box">
                                                <div class="col-sm-4">
                                                    <div class="text-center">
                                                        <img alt="image" class="m-t-xs img-responsive"
                                                             src="/sys/downloadServlet?fileName=${good.img}">
                                                        <div class="m-t-xs font-bold">CTO</div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-8">
                                                    <h3><strong>${good.name}</strong></h3>
                                                    <p><i class="fa fa-map-marker"></i> ${good.make}</p>
                                                    <address>
                                                        价格：<strong style="color:red">${good.sellprice}</strong><br>
                                                        型号：${good.size}<br>
                                                        生产时间：<a href="">${good.maketime}</a><br>
                                                        <c:if test="${good.count!=0}">
                                                            <a href="/store/cartServlet?action=saveOrUpdatePage&id=${good.id}" type="button" style="margin-top: 5px;" class="btn btn-w-m btn-danger">加入购物车</a>
                                                        </c:if>
                                                        <c:if test="${good.count==0}">
                                                            <button type="button" style="margin-top: 5px;" class="btn btn-w-m btn-default">已售罄</button>
                                                        </c:if>

                                                    </address>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>


            </div>
        </div>

    </div>
</div>
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/js/plugins/peity/jquery.peity.min.js"></script>
<script src="/js/content.min.js?v=1.0.0"></script>
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script src="/js/demo/peity-demo.min.js"></script>
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>
<script>
    $(document).ready(function(){
        $(".i-checks").iCheck({
            checkboxClass:"icheckbox_square-green",
            radioClass:"iradio_square-green",
        });
        $(".tabs-container .nav-tabs li").click(function(data){
            var href = $(this).children()[0].href;
            //做字符串截取,获取点击的标签id
            var aId=href.substring(href.lastIndexOf('tab-'),href.length);
            //先给所有的class=tab-pane 的都移除 active属性
            $(".tab-pane").removeClass('active');
            //然后单独给当前点击的添加active属性
            $("#"+aId).addClass('active')
        });
        initTab();
    });

   function initTab() {
       var li=$(".tabs-container .nav-tabs").children()[0];
       $(li).addClass('active');
      var href=$($(".tabs-container .nav-tabs").children()[0]).children()[0].href
       //做字符串截取,获取点击的标签id
       var aId=href.substring(href.lastIndexOf('tab-'),href.length);
       //先给所有的class=tab-pane 的都移除 active属性
       $(".tab-pane").removeClass('active');
       //然后单独给当前点击的添加active属性
       $("#"+aId).addClass('active')

   }


    function removeDate(id){
        swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        }, function () {
            swal("删除成功！", "您已经永久删除了这条信息。", "success");
            $.get("/sys/userServlet?action=remove&id="+id,function(msg){
                //发起查询操作
                window.location.href="/store/showServlet?action=list";
            })
        });
    }

</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
</html>