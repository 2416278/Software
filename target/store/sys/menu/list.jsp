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
    <title>菜单管理</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单管理</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="table_basic.html#">选项1</a>
                            </li>
                            <li><a href="table_basic.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-9 m-b-xs">
                            <a href="/sys/menuServlet?action=saveOrUpdatePage" class="btn btn-success " type="button"><i class="fa fa-plus"></i>&nbsp;添加</a>

                        </div>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <form action="/sys/menuServlet" id="myForm" method="get">
                                    <div class="input-group">
                                        <input type="text" name="key" value="${pageUtils.key}"placeholder="请输入关键词" class="input-sm form-control">
                                        <span class="input-group-btn">
                                            <button type="submit"  class="btn btn-sm btn-primary"> 搜索</button>
                                        </span>
                                        <input type="hidden" name="action" value="list">

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th></th>
                                <th>编号</th>
                                <th>名称</th>
                                <th>地址</th>
                                <th>序号</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.list}" var="entity">
                                <c:if test="${entity.parentId==-1}">
                                    <tr>
                                        <td>
                                           <input type="checkbox" checked class="i-checks" name="input[]">
                                       </td>
                                       <td>${entity.id}</td>
                                       <td>${entity.name}</td>
                                       <td>${entity.url}</td>
                                       <td>${entity.seq}</td>
                                       <td>
                                          <a href="/sys/menuServlet?action=saveOrUpdatePage&id=${entity.id}&name=${entity.name}" class="btn btn-info " type="button"><i class="fa fa-edit"></i>&nbsp;更新</a>
                                          <button class="btn btn-danger" onclick="removeDate(${entity.id})" type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                       </td>
                                    </tr>
                                    <c:forEach items="${list}" var="sub">
                                        <c:if test="${sub.parentId==entity.id}">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" checked class="i-checks" name="input[]">
                                                </td>
                                                <td>&nbsp;&nbsp;&nbsp;&nbsp;${sub.id}</td>
                                                <td>&nbsp;&nbsp;&nbsp;&nbsp;${sub.name}</td>
                                                <td>${sub.url}</td>
                                                <td>${sub.seq}</td>
                                                <td>
                                                    <a href="/sys/menuServlet?action=saveOrUpdatePage&id=${sub.id}&name=${sub.name}" class="btn btn-info " type="button"><i class="fa fa-edit"></i>&nbsp;更新</a>
                                                    <button class="btn btn-danger" onclick="removeDate(${sub.id})" type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                                </td>
                                            </tr>

                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
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
    });

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
            $.get("/sys/menuServlet?action=remove&id="+id,function(msg){
                if(msg=="ok"){
                    //删除成功
                    window.location.href="/sys/menuServlet?action=list";
                }
                else{
                    //表示不能删除
                    swal("删除失败！","该菜单不能被删除","warning");
                }


            })
        });
    }

</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
</html>