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
    <title>购物车</title>
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
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>购物车</h5>
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
                    <div class="row">
                        <div class="col-sm-9 m-b-xs">
                            <a href="/store/cartServlet?action=saveOrUpdatePage" class="btn btn-success " type="button"><i class="fa fa-plus"></i>&nbsp;添加</a>
                        </div>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <form action="/store/cartServlet" id="myForm" method="get">
                                    <div class="input-group">
                                        <input type="text" name="key" value="${pageUtils.key}"placeholder="请输入关键词" class="input-sm form-control">
                                        <span class="input-group-btn">
                                            <button type="submit"  class="btn btn-sm btn-primary"> 搜索</button>
                                        </span>
                                        <input type="hidden" name="action" value="list">
                                        <input type="hidden" id="pageNum" name="pageNum" value="${pageUtils.pageNum}">
                                        <input type="hidden" id="pageSize" name="pageSize" value="${pageUtils.pageSize}">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td><input type="checkbox" id="all"></td>
                                <th>商品图片</th>
                                <th>商品名称</th>
                                <th>商品售价</th>
                                <th>数量</th>
                                <th>总价</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.pageUtils.list}" var="entity">
                                <tr>
                                    <td><input type="checkbox" id="check"></td>
                                    <td><img src="/sys/downloadServlet?fileName=${entity.img}" style="width:60px;height:60px;"></td>
                                    <td>${entity.name}</td>
                                    <td>${entity.price}</td>
                                    <td><button class="cal" >-</button><input type="text" value=${entity.amount} readonly id="amount"><button class="cal">+</button></td>
                                    <td class="total">${entity.price * entity.amount} </td>
                                    <td>
                                        <button class="btn btn-danger" onclick="removeDate(${entity.id})" type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                   <%@ include file="/common/commonPage.jsp"%>

                    <div class="row">
                        <div class="col-sm-9 m-b-xs">
                         </div>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <span>商品总计：</span><span class="sumTotal"></span><button class="btn btn-warning " id="payButton" onclick="pay()">结算</button>
                            </div>
                        </div>
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
            $.get("/store/cartServlet?action=remove&id="+id,function(msg){
                //发起查询操作
                window.location.href="/store/cartServlet?action=list";
            })
        });
    }


    //单价
    let _cals = document.querySelectorAll('.cal')
    _cals.forEach(_cal => {
        _cal.onclick = function () {
            let opera = _cal.innerHTML
            let _input = this.parentNode.childNodes[1]
            if (_input.value <= 0 && opera == '-') {
                return
            }
            let num = eval(_input.value * 1 + opera + 1)
            _input.value = num
            var preElement = this.parentNode.previousElementSibling;
            let name=preElement.previousElementSibling.innerHTML;
           if(num==0){
               //删除
               $.get("/store/cartServlet?action=removeByName&name="+name,function(msg){
                   //发起查询操作
                   window.location.href="/store/cartServlet?action=list";
               })
           }else{
               $.get("/store/cartServlet?action=saveByName&name="+name+ "&amount="+num,function(msg){
                   //保存数量
                   //window.location.href="/store/cartServlet?action=list";
               })
           }

            let price = this.parentNode.previousElementSibling.innerHTML * 1
            let total = price * num
            this.parentNode.nextElementSibling.innerHTML = total
            sumTotal()
        }
    })
    //多选
    let _all = document.querySelector('#all')
    let _check = document.querySelectorAll('#check')
    _all.onclick = function () {
        _check.forEach(_check => {
            _check.checked = _all.checked

        })
        sumTotal()
    }
    //反选
    _check.forEach(radio => {
        radio.onclick = function () {
            let _checkedboxes = document.querySelectorAll('input:checked:not(#all)')
            if (_check.length == _checkedboxes.length) {
                _all.checked = true
            } else {
                _all.checked = false
            }
            sumTotal()
        }
    })
    //总和
    let _sumTotal = document.querySelector('.sumTotal')
    function sumTotal() {
        let _checkedboxes = document.querySelectorAll('input:checked:not(#all)')
        let sumTotal = 0
        _checkedboxes.forEach(_checkedbox => {
            if (_checkedbox.checked) {
                sumTotal += _checkedbox.parentNode.parentNode.children[5].innerText * 1
                let preElement = _checkedbox.parentNode.nextElementSibling;
                let name = preElement.nextElementSibling.innerHTML;
                // 调用保存到订单数据库中
                $.get("/store/orderServlet?action=saveByName&name=" + name);
            }
        })
        _sumTotal.innerHTML = sumTotal
    }
    function pay() {
        var sumTotal = parseFloat(document.querySelector('.sumTotal').textContent);
        if (sumTotal > 0) {
            window.location.href = "/pay.jsp";
        }
    }
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
</html>